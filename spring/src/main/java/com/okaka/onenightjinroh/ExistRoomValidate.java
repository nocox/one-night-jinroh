package com.okaka.onenightjinroh;

import com.okaka.jinroh.persistence.Room;
import com.okaka.jinroh.persistence.RoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExistRoomValidate {
    @Autowired
    RoomDao roomDao;

    public Optional<Room> existRoom(String uuid){
        return roomDao.selectRoomByUUID(uuid);
    }
}
