package com.okaka.onenightjinroh.application.service

import com.okaka.onenightjinroh.application.domain.*
import com.okaka.onenightjinroh.application.repository.GameParticipantRepository
import com.okaka.onenightjinroh.application.repository.GameRepository
import com.okaka.onenightjinroh.application.repository.RoleNightActFormatterRepository
import org.springframework.stereotype.Service

@Service
class GetGameIndexUseCase(
    private val gameRepository: GameRepository,
    private val gameParticipantRepository: GameParticipantRepository,
    private val roleNightActFormatterRepository: RoleNightActFormatterRepository,
) {
    operator fun invoke(gameId: Long, participantId: Long, term: GameTerm): Dto {
        val game = gameRepository.find(gameId) ?: return GameNotStarted
        if (game.term != term) {
            return GameTermDto(game.term!!)
        }

        val gameParticipants = GameParticipants.of(gameParticipantRepository.findByGameIdWithUserAndRole(gameId))
        val roleNightActFormatter: RoleNightActFormatter? =
            roleNightActFormatterRepository.fetchNightAct(gameId, participantId).orElse(null)
        val myNightActLog: String = roleNightActFormatter?.toActLog() ?: ""
        val displayableParticipantIdToRoles = getDisplayableParticipantIdAndRoles(gameId, participantId)

        return of(gameParticipants, participantId, myNightActLog, displayableParticipantIdToRoles)
    }

    private fun of(
        gameParticipants: GameParticipants,
        participantId: Long,
        nightActLog: String,
        displayableParticipantIdToRoles: Map<Long, Role>,
    ): GameIndexDto {
        val myself = gameParticipants.participants
            .first { it.gameParticipationId == participantId }
        val otherGameParticipants = gameParticipants.participants
            .filter { it.gameParticipationId != participantId }
            .map {
                it.changeDisplayableRole(
                    displayableParticipantIdToRoles[it.gameParticipationId]
                )
            }

        return GameIndexDto(
            playerId = myself.gameParticipationId,
            playerName = myself.user.userName,
            // 怪盗の場合は自分の役職が変わるためここで取得する（NULLにはならない）
            playerRole = displayableParticipantIdToRoles[myself.gameParticipationId]!!,
            hostFlag = myself.hostFlg,
            otherPlayerList = otherGameParticipants,
            nightActLog = nightActLog
        )
    }

    // 昔のロジックをそのまま流用している。(displayChecker)
    // Beanで返す方式でコードが組まれているので改善する必要がある。
    // 本来は、ドメイン知識として扱いたい。
    fun getDisplayableParticipantIdAndRoles(
        gameId: Long,
        gameParticipantId: Long,
    ): Map<Long, Role> {
        val gameParticipants = GameParticipants.of(gameParticipantRepository.findByGameIdWithUserAndRole(gameId))
        val roleNightActFormatter = roleNightActFormatterRepository.fetchNightAct(gameId, gameParticipantId)
        val displayChecker =
            ParticipantDisplayChecker.of(gameParticipants.mySelf(gameParticipantId), roleNightActFormatter.orElse(null))

        val participants = gameParticipants.participants.map { displayChecker.check(it) }
            .filter { it.role.roleId != Role.UNKNOWN_ROLE_ID }

        return participants.associate {
            it.id to Role.byRoleId(it.role.roleId, it.role.roleName)
        }
    }

    sealed interface Dto

    class GameIndexDto(
        val playerId: Long,
        val playerName: String,
        val playerRole: Role,
        val hostFlag: Boolean,
        val otherPlayerList: List<GameParticipant>,
        val nightActLog: String,
    ) : Dto

    class GameTermDto(
        val term: GameTerm
    ) : Dto

    object GameNotStarted : Dto
}