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

    // FIXME: 実装中のため一時的にrequiredをfalseにしているが、最終的にはtrueにしたい
    @RequestMapping(path = ["/game-index"])
    fun getGameRule(session: HttpSession, @RequestParam(required = false, defaultValue = "night") term: String): Response {
        val strGameId: String = session.getAttribute("game_id").toString()
        val gameId = strGameId.toLong()

        val strGameParticipationId: String = session.getAttribute("game_participation_id").toString()
        val gameParticipantId = strGameParticipationId.toLong()

        val dto = getGameIndexUseCase(gameId, gameParticipantId, toGameTerm(term))
        return Response(
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
            ""
        )
    }


    class Response(
        val playerId: Long,
        val playerName: String,
        val playerRole: RoleResponse,
        val hostFlag: Boolean,
        val otherPlayerList: List<GameParticipantResponse>,
        val nightActLog: String
    )

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