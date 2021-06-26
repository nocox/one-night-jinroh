package com.okaka.onenightjinroh.application.validater;

import com.okaka.jinroh.persistence.RoomDao;
import com.okaka.jinroh.persistence.RoomEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExistRoomValidate {
    @Autowired
    RoomDao roomDao;

    public Optional<RoomEntity> existRoom(String uuid){
        return roomDao.selectRoomByUUID(uuid);
    }
}
