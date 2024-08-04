package com.okaka.onenightjinroh.application.service.room

import com.okaka.onenightjinroh.application.domain.RoomStatus
import com.okaka.onenightjinroh.application.repository.RoomParticipantRepository
import com.okaka.onenightjinroh.application.repository.RoomRepository
import com.okaka.onenightjinroh.application.exception.RoomNotExistException
import com.okaka.onenightjinroh.application.validater.ExistRoomValidate
import org.springframework.stereotype.Service

@Service
class FinishRoomUseCase(
    private val roomParticipantRepository: RoomParticipantRepository,
    private val roomRepository: RoomRepository,
    private val existRoomValidate: ExistRoomValidate,
) {
    fun invoke(userId: Long?, roomUuid: String?) {
        val roomParticipant = roomParticipantRepository.findByUserId(userId).orElseThrow { IllegalArgumentException() }
        val room = existRoomValidate.existRoom(roomUuid).orElseThrow { RoomNotExistException("")}


        // ルーム解散を特別に一旦ホスト以外もできるようにした。不要なルームを残さないため。 (24/7)
        // if (!roomParticipant.hostFlg) {
        //     throw new IllegalArgumentException();
        // }
        require(RoomStatus.Finished != room.getStatus())

        val finishedRoom = room.finishRoom()
        roomRepository.save(finishedRoom)
    }
}
