package com.okaka.onenightjinroh.api

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession

@RestController
class FetchJoinedRoom {
    @RequestMapping(path = ["/joined-room"])
    fun getJoinedRoom(session: HttpSession) : Response {
        if (session.getAttribute("room_uuid") != null ) {
            return Response(status = ResponseStatus.JOINED_ROOM)
        } else {
            return Response(status = ResponseStatus.NOT_JOINED_ROOM)
        }
    }

    data class Response(val status: ResponseStatus)

    enum class ResponseStatus{
        JOINED_ROOM,
        NOT_JOINED_ROOM
    }
}