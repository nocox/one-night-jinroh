package com.okaka.onenightjinroh.api

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession

@RestController
class ExitRoomController {
    @PostMapping(path = ["/exit-room"])
    fun exitRoom(model: Model, session: HttpSession): Int {
        session.removeAttribute("room_uuid")
        session.removeAttribute("user_id")
        return 0
    }
}