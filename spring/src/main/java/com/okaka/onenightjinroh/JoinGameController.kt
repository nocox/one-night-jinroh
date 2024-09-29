package com.okaka.onenightjinroh

import com.okaka.onenightjinroh.application.service.night.GetGamePersonalUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession

@RestController
class JoinGameController(
    private val getGamePersonalUseCase: GetGamePersonalUseCase,
) {

    @PostMapping(path = ["/join-game"])
    fun joinGame(@RequestParam gameId: Long, session: HttpSession): String {

        val uuid: String = session.getAttribute("room_uuid").toString()
        val strUserId: String = session.getAttribute("user_id").toString()
        val userId = strUserId.toLong()

        // 参加者IDの取得と同時に照合も行う
        val gameParticipantId = getGamePersonalUseCase.get(gameId, uuid, userId)

        session.setAttribute("game_id", gameId)
        session.setAttribute("game_participation_id", gameParticipantId)

        return "JOIN_GAME_SUCCESS"
    }
}