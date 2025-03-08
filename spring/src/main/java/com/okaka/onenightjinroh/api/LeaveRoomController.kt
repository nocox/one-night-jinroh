package com.okaka.onenightjinroh.api

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession

@RestController
class LeaveRoomController {
    @PostMapping(path = ["/leave-room"])
    fun exitRoom(model: Model, session: HttpSession): Response {
        session.removeAttribute("room_uuid")
        session.removeAttribute("user_id")

        val gameId = session.getAttribute("game_id")
        if (gameId != null) {
            session.removeAttribute("game_id")
            session.removeAttribute("game_participation_id")
        }
        return Response(ResponseStatus.SUCCESS)
    }

    enum class ResponseStatus{
        SUCCESS,
    }

    data class Response(val status: ResponseStatus)
}