package com.okaka.onenightjinroh.api

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession


@RestController
class FetchGameIdController {
    @RequestMapping(path = ["/fetch-game-id"])
    fun getJoinedRoom(session: HttpSession) : Response {
        return if (session.getAttribute("game_id") != null ) {
            val gameId = session.getAttribute("game_id").toString()
            SuccessResponse(gameId = gameId)
        } else {
            FailResponse
        }
    }

    sealed interface Response {
        val resultCode: String
    }

    class SuccessResponse(val gameId: String) : Response {
        override val resultCode: String = "IN_GAME"
    }

    object FailResponse : Response {
        override val resultCode: String = "NOT_IN_GAME"
    }
}
