package com.okaka.onenightjinroh.application.service.room;

import com.okaka.onenightjinroh.application.domain.Room;
import com.okaka.onenightjinroh.application.domain.RoomParticipant;
import com.okaka.onenightjinroh.application.domain.RoomStatus;
import com.okaka.onenightjinroh.application.repository.RoomParticipantRepository;
import com.okaka.onenightjinroh.application.repository.RoomRepository;
import com.okaka.onenightjinroh.application.validater.ExistRoomValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinishRoomUseCase {
    @Autowired
    private RoomParticipantRepository roomParticipantRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ExistRoomValidate existRoomValidate;

    public void invoke(Long userId, String roomUuid) {
        RoomParticipant roomParticipant = roomParticipantRepository.findByUserId(userId).orElseThrow(IllegalArgumentException::new);
        Room room = existRoomValidate.existRoom(roomUuid).orElseThrow(IllegalArgumentException::new);

        if (!roomParticipant.hostFlg) {
            // Note: この場合、悪意がある可能性があるので、ログとか流すべきかも
            throw new IllegalArgumentException();
        }

        if (RoomStatus.Finished.equals(room.getStatus())) {
            throw new IllegalArgumentException();
        }

        Room finishedRoom = room.finishRoom();
        roomRepository.save(finishedRoom);
    }
}
