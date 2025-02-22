package com.okaka.onenightjinroh.api

import com.okaka.onenightjinroh.application.service.room.GetRoomIndexUseCase
import com.okaka.onenightjinroh.application.service.room.NotEnoughParticipantsException
import com.okaka.onenightjinroh.application.service.room.RoomIndexBean
import com.okaka.onenightjinroh.application.service.room.StartGameUseCase
import com.okaka.onenightjinroh.application.validater.ExistRoomValidate
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession

@RestController
class RoomController(
private val session: HttpSession,
private val messagingTemplate: SimpMessagingTemplate,
private var getRoomIndexUseCase: GetRoomIndexUseCase,
private val existRoomValidate: ExistRoomValidate,
private val startGameUseCase: StartGameUseCase,
) {

    @get:RequestMapping(path = ["/room-index"])
    val room: RoomIndexBean
        get() {
            val uuid = session.getAttribute("room_uuid").toString()
            val strUserId = session.getAttribute("user_id").toString()
            val userId = strUserId.toLong()

            return getRoomIndexUseCase.getRoomIndex(userId, uuid)
        }

    @RequestMapping(path = ["/game-start"])
    fun startGame(): Response {
        val uuid = session.getAttribute("room_uuid").toString()
        val userId = session.getAttribute("user_id").toString().toLong()

        val room = existRoomValidate.existRoom(uuid).orElseThrow { IllegalArgumentException() }

        try {
            val gameStartWebSocketBean = startGameUseCase.startGame(room.getRoomId(), userId)
            // ここでブロードキャストする
            messagingTemplate.convertAndSend("/topic/" + room.uuid, gameStartWebSocketBean)
            return Response(status = ResponseStatus.SUCCESS)
        } catch (e: NotEnoughParticipantsException) {
            return Response(status = ResponseStatus.NOT_ENOUGH_PARTICIPANTS)
        }
    }

    data class Response(val status: ResponseStatus)

    enum class ResponseStatus{
        SUCCESS,
        NOT_ENOUGH_PARTICIPANTS
    }
}
