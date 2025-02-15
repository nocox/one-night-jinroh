package com.okaka.onenightjinroh.application.service.room

import com.okaka.onenightjinroh.application.domain.GameTerm
import com.okaka.onenightjinroh.application.domain.RoomStatus
import com.okaka.onenightjinroh.application.repository.RoomParticipantRepository
import com.okaka.onenightjinroh.application.repository.RoomRepository
import com.okaka.onenightjinroh.application.exception.RoomNotExistException
import com.okaka.onenightjinroh.application.repository.GameRepository
import com.okaka.onenightjinroh.application.validater.ExistRoomValidate
import org.springframework.stereotype.Service

@Service
class FinishRoomUseCase(
    private val roomRepository: RoomRepository,
    private val gameRepository: GameRepository,
    private val existRoomValidate: ExistRoomValidate,
) {
    fun invoke(userId: Long?, roomUuid: String?) {
        val room = existRoomValidate.existRoom(roomUuid).orElseThrow { RoomNotExistException("")}

        // ルーム解散を特別に一旦ホスト以外もできるようにした。不要なルームを残さないため。 (24/7)
        // val roomParticipant = roomParticipantRepository.findByUserId(userId).orElseThrow { IllegalArgumentException() }
        // if (!roomParticipant.hostFlg) {
        //     throw new IllegalArgumentException();
        // }

        require(RoomStatus.Finished != room.getStatus())

        val finishedRoom = room.finishRoom()
        roomRepository.save(finishedRoom)

        // TODO: Gameを終わらせる必要があるんだけど、そのためのステータスがない。。。（追加が必要か？それとも別の方法を模索するか？）
        val games = gameRepository.findByRoomId(room.roomId)
        games.forEach { game ->
            val changedGame = game.changeTerm(GameTerm.RESULT)
            gameRepository.save(changedGame)
        }
    }
}
