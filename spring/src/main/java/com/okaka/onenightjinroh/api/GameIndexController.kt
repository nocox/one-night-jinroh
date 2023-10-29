package com.okaka.onenightjinroh.api

import com.okaka.onenightjinroh.application.service.GetGameIndexUseCase
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession

@RestController
class GameIndexController(
    private val getGameIndexUseCase: GetGameIndexUseCase,
) {

    @RequestMapping(path = ["/game-index"])
    fun getGameRule(session: HttpSession): Response {
        val strGameId: String = session.getAttribute("game_id").toString()
        val gameId = strGameId.toLong()

        val strGameParticipationId: String = session.getAttribute("game_participation_id").toString()
        val gameParticipantId = strGameParticipationId.toLong()

        val dto = getGameIndexUseCase(gameId, gameParticipantId)
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