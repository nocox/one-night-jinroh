package com.okaka.onenightjinroh.api

import com.okaka.onenightjinroh.application.service.top.JoinedRoomUseCase
import com.okaka.onenightjinroh.application.service.top.JoinedRoomUseCaseDto
import com.okaka.onenightjinroh.application.exception.ParticipantLimitException
import com.okaka.onenightjinroh.application.exception.RoomNotExistException
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession


@RestController
class JoinRoomController(
    private val joinedRoomUseCase: JoinedRoomUseCase
) {
    @RequestMapping(path = ["/join-room"])
    fun joinedRoom(@RequestParam uuid: String?, session: HttpSession): Response {
        if (session.getAttribute("room_uuid") == uuid) {
            return Response(status = ResponseStatus.ALREADY_JOINED)
        } else if (session.getAttribute("room_uuid") != null) {
            return Response(status = ResponseStatus.OTHER_ROOM_JOINED)
        }

        try {
            val dto: JoinedRoomUseCaseDto = joinedRoomUseCase.joinedRoom(uuid)
            session.setAttribute("user_id", dto.userEntity.user_id)
            session.setAttribute("room_uuid", uuid)
            return Response(status = ResponseStatus.JOIN_SUCCESS)
        } catch (e: RoomNotExistException) {
            return Response(status = ResponseStatus.ROOM_NOT_EXIST)
        } catch (e: ParticipantLimitException) {
            return Response(status = ResponseStatus.PARTICPANT_LIMIT)
        }
    }

    data class Response(val status: ResponseStatus)

    enum class ResponseStatus{
        ALREADY_JOINED,
        OTHER_ROOM_JOINED,
        JOIN_SUCCESS,
        ROOM_NOT_EXIST,
        PARTICPANT_LIMIT
    }
}

