package com.okaka.onenightjinroh.api

import com.okaka.onenightjinroh.application.exception.RoomNotExistException
import com.okaka.onenightjinroh.application.service.room.FinishRoomUseCase
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession

@RestController
class FinishRoomController(
    private val finishRoomUseCase: FinishRoomUseCase,
    private val messagingTemplate: SimpMessagingTemplate

) {
    @RequestMapping(path = ["/room-finish"])
    fun finishRoom(session: HttpSession): Response {
        val uuid: String = session.getAttribute("room_uuid").toString()
        val userId: Long = session.getAttribute("user_id").toString().toLong()

        try {
            finishRoomUseCase.invoke(userId, uuid)
        } catch (e: RoomNotExistException) {
            return  Response(status = ResponseStatus.ROOM_NOT_EXIST)
        }

        session.removeAttribute("room_uuid")
        messagingTemplate.convertAndSend("/topic/receive-finish-room/$uuid", "")

        // ゲームが存在したら参加者に削除依頼
        val gameId = session.getAttribute("game_id")
        if (gameId != null) {
            messagingTemplate.convertAndSend("/topic/${gameId}/leave-room", "")
        }

        return Response(status = ResponseStatus.FINISHED_ROOM)
    }

    data class Response(val status: ResponseStatus)

    enum class ResponseStatus{
        ROOM_NOT_EXIST,
        FINISHED_ROOM
    }
}