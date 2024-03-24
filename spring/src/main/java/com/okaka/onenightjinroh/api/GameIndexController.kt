package com.okaka.onenightjinroh.api

import com.okaka.onenightjinroh.application.domain.GameTerm
import com.okaka.onenightjinroh.application.service.GetGameIndexUseCase
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession

@RestController
class GameIndexController(
    private val getGameIndexUseCase: GetGameIndexUseCase,
) {

    @RequestMapping(path = ["/game-index"])
    fun getGameRule(session: HttpSession, @RequestParam(required = true) term: String): Response {
        val sessionGameId = session.getAttribute("game_id") ?: return GameNotStartedResponse("ゲームが始まっていません")
        val gameId = sessionGameId.toString().toLong()

        val strGameParticipationId: String = session.getAttribute("game_participation_id").toString()
        val gameParticipantId = strGameParticipationId.toLong()

        return when(val dto = getGameIndexUseCase(gameId, gameParticipantId, toGameTerm(term))) {
            is GetGameIndexUseCase.GameIndexDto -> GameIndexResponse(
                    dto.playerId,
                    dto.playerName,
                    RoleResponse(
                        dto.playerRole.roleId,
                        dto.playerRole.roleName
                    ),
                    dto.hostFlag,
                    dto.otherPlayerList.map {
                        GameParticipantResponse(
                            it.gameParticipationId,
                            it.user.userName,
                            RoleResponse(
                                it.role.roleId,
                                it.role.roleName
                            ),
                            it.hostFlg
                        )
                    },
                    dto.nightActLog
                )
            is GetGameIndexUseCase.GameTermDto -> GameTermResponse(dto.term.code)
            GetGameIndexUseCase.GameNotStarted -> GameNotStartedResponse("ゲームが始まっていません")
        }


    }


    sealed interface Response {
        val type: String
    }

    class GameIndexResponse(
        val playerId: Long,
        val playerName: String,
        val playerRole: RoleResponse,
        val hostFlag: Boolean,
        val otherPlayerList: List<GameParticipantResponse>,
        val nightActLog: String,
    ): Response {
        override val type: String = "GameIndex"
    }

    class GameTermResponse(
        val term: String
    ): Response {
        override val type: String = "TermIsDifferent"
    }

    class GameNotStartedResponse(
        val errorMessage: String
    ): Response {
        override val type: String = "NotStared"
    }


    fun toGameTerm(term: String): GameTerm {
        return when(term) {
            "night" -> GameTerm.NIGHT
            "talk" -> GameTerm.TALK
            "vote" -> GameTerm.VOTE
            "tally" -> GameTerm.TALLY
            "result" -> GameTerm.RESULT
            else -> throw IllegalArgumentException()
        }
    }

    class RoleResponse(
        val roleId: Long,
        val roleName: String
    )

    class GameParticipantResponse(
        val id: Long,
        val name: String,
        val role: RoleResponse,
        val hostFlag: Boolean
    )
}