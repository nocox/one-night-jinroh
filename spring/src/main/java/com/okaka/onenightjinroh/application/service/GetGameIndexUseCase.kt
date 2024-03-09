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
    private val roleNightActFormatterRepository: RoleNightActFormatterRepository
) {
    operator fun invoke(gameId: Long, participantId: Long, term: GameTerm): Dto {
        val game = gameRepository.find(gameId)
        if (game.term != term) {
            throw IllegalArgumentException("進行状況が一致しません")
        }

        val gameParticipants = GameParticipants.of(gameParticipantRepository.findByGameIdWithUserAndRole(gameId))
        val roleNightActFormatter: RoleNightActFormatter? =
            roleNightActFormatterRepository.fetchNightAct(gameId, participantId).orElse(null)
        val myNightActLog: String = roleNightActFormatter?.toActLog() ?: ""
        val displayableParticipantIds = getDisplayableParticipantId(gameId, participantId)

        return of(gameParticipants, participantId, myNightActLog, displayableParticipantIds)
    }

    private fun of(
        gameParticipants: GameParticipants,
        participantId: Long,
        nightActLog: String,
        displayableParticipantIds: Set<Long>
    ): Dto {
        val myself = gameParticipants.participants.first { it.gameParticipationId == participantId }
        val otherGameParticipants = gameParticipants.participants
            .filter { it.gameParticipationId != participantId }
            .map { it.also { if (!displayableParticipantIds.contains(it.gameParticipationId)) it.setUnknownRole() } }
        return Dto(
            myself.gameParticipationId,
            myself.user.userName,
            myself.role,
            myself.hostFlg,
            otherGameParticipants,
            nightActLog
        )
    }

    // 昔のロジックをそのまま流用している。(displayChecker)
    // Beanで返す方式でコードが組まれているので改善する必要がある。
    // 本来は、ドメイン知識として扱いたい。
    fun getDisplayableParticipantId(
        gameId: Long,
        gameParticipantId: Long,
    ): Set<Long> {
        val gameParticipants = GameParticipants.of(gameParticipantRepository.findByGameIdWithUserAndRole(gameId))
        val roleNightActFormatter = roleNightActFormatterRepository.fetchNightAct(gameId, gameParticipantId)
        val displayChecker =
            ParticipantDisplayChecker.of(gameParticipants.mySelf(gameParticipantId), roleNightActFormatter.orElse(null))

        return gameParticipants.participants.map { displayChecker.check(it) }
            .filter { it.role.roleId != Role.UNKNOWN_ROLE_ID }
            .map { it.id }
            .toSet()
    }

    class Dto(
        val playerId: Long,
        val playerName: String,
        val playerRole: Role,
        val hostFlag: Boolean,
        val otherPlayerList: List<GameParticipant>,
        val nightActLog: String,
    )
}