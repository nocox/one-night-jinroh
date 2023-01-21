package com.okaka.onenightjinroh.application.validater;

import com.okaka.onenightjinroh.application.domain.Room;
import com.okaka.onenightjinroh.application.domain.RoomStatus;
import com.okaka.onenightjinroh.application.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExistRoomValidate {
    @Autowired
    RoomRepository roomRepository;

    public Optional<Room> existRoom(String uuid){
        Optional<Room> optRoom = roomRepository.findByUUID(uuid);
        if (optRoom.isEmpty()){
            return Optional.empty();
        }
        // Note: 処理を大きく変えないために、いったんOptionalで対応した.
        // 取得できなかった理由を知りたい場合には、ここで例外を投げれるように修正したい。
        if (RoomStatus.Finished.equals(optRoom.get().status)) {
            return Optional.empty();
        }
        return optRoom;
    }
}
