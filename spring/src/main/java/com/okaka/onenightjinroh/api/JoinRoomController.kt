package com.okaka.onenightjinroh.api

import com.okaka.onenightjinroh.application.service.top.JoinedRoomUseCase
import com.okaka.onenightjinroh.application.service.top.JoinedRoomUseCaseDto
import com.okaka.onenightjinroh.application.service.top.ParticipantLimitException
import com.okaka.onenightjinroh.application.service.top.RoomNotExistException
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession


@RestController
class JoinRoomController(
    private val joinedRoomUseCase: JoinedRoomUseCase
) {
    @RequestMapping(path = ["/join-room"])
    fun joinedRoom(@RequestParam uuid: String?, session: HttpSession): String {
        if (session.getAttribute("room_uuid") == uuid) {
            return "ALREADY_JOINED"
        } else if (session.getAttribute("room_uuid") != null) {
            return "OTHER_ROOM_JOINED"
        }

        try {
            val dto: JoinedRoomUseCaseDto = joinedRoomUseCase.joinedRoom(uuid)
            session.setAttribute("user_id", dto.userEntity.user_id)
            session.setAttribute("room_uuid", uuid)
            return "JOIN_SUCCESS"
        } catch (e: RoomNotExistException) {
            return "ROOM_NOT_EXIST"
        } catch (e: ParticipantLimitException) {
            return "PARTICPANT_LIMIT"
        }
    }
}

