package com.okaka.onenightjinroh.api

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession

@RestController
class FetchJoinedRoom {
    @RequestMapping(path = ["/joined-room"])
    fun getJoinedRoom(session: HttpSession) : String {
        if (session.getAttribute("room_uuid") != null ) {
            return "JOINED_ROOM"
        } else {
            return "NOT_JOINED_ROOM"
        }
    }
}