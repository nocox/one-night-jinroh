package com.okaka.onenightjinroh.application.domain;

import com.okaka.jinroh.persistence.RoomEntity;
import com.okaka.jinroh.persistence.RoomDao;
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
