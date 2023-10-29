package com.okaka.onenightjinroh.application.service

import com.okaka.onenightjinroh.application.domain.GameParticipant
import com.okaka.onenightjinroh.application.domain.GameParticipants
import com.okaka.onenightjinroh.application.domain.Role
import com.okaka.onenightjinroh.application.domain.RoleNightActFormatter
import com.okaka.onenightjinroh.application.repository.GameParticipantRepository
import com.okaka.onenightjinroh.application.repository.RoleNightActFormatterRepository
import org.springframework.stereotype.Service

@Service
class GetGameIndexUseCase(
    private val gameParticipantRepository: GameParticipantRepository,
    private val roleNightActFormatterRepository: RoleNightActFormatterRepository

) {
    operator fun invoke(gameId: Long, participantId: Long): Dto {
        val gameParticipants = GameParticipants.of(gameParticipantRepository.findByGameIdWithUserAndRole(gameId))
        val roleNightActFormatter: RoleNightActFormatter? =
            roleNightActFormatterRepository.fetchNightAct(gameId, participantId).orElse(null)
        val myNightActLog: String = roleNightActFormatter?.toActLog() ?: ""

        return of(gameParticipants, participantId, myNightActLog)
    }

    private fun of(gameParticipants: GameParticipants, participantId: Long, nightActLog: String): Dto {
        val myself = gameParticipants.participants.first { it.gameParticipationId == participantId }
        val otherGameParticipants = gameParticipants.participants
            .filter { it.gameParticipationId != participantId }
            .toList()
        return Dto(
            myself.gameParticipationId,
            myself.user.userName,
            myself.role,
            myself.hostFlg,
            otherGameParticipants,
            nightActLog
        )
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