package com.okaka.onenightjinroh.api

import com.okaka.onenightjinroh.application.service.top.CreateRoomUseCase
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession

@RestController
class CreateRoomController(
    private val createRoomUseCase: CreateRoomUseCase
) {
    @RequestMapping(path = ["/create-room"])
    fun createRoom(session: HttpSession): String {
        if (session.getAttribute("room_uuid") != null) {
            return "OTHER_ROOM_JOINED"
        }

        val dto = createRoomUseCase.createRoom()

        session.setAttribute("user_id", dto.userEntity.user_id)
        session.setAttribute("room_uuid", dto.room.uuid)
        return "CREATE_ROOM_SUCCESS"
    }
}
