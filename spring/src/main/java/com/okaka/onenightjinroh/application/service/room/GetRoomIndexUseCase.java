package com.okaka.onenightjinroh.application.service.room;

import com.okaka.jinroh.persistence.RoomEntity;
import com.okaka.jinroh.persistence.RoomParticipantDao;
import com.okaka.jinroh.persistence.UserDao;
import com.okaka.jinroh.persistence.UserEntity;
import com.okaka.onenightjinroh.application.domain.Room;
import com.okaka.onenightjinroh.application.validater.ExistRoomValidate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetRoomIndexUseCase {
    private final UserDao userDao;
    private final RoomParticipantDao roomParticipantDao;
    private final ExistRoomValidate existRoomValidate;

    public GetRoomIndexUseCase(UserDao userDao, RoomParticipantDao roomParticipantDao, ExistRoomValidate existRoomValidate) {
        this.userDao = userDao;
        this.roomParticipantDao = roomParticipantDao;
        this.existRoomValidate = existRoomValidate;
    }

    public RoomIndexBean getRoomIndex(Long userId, String uuid) {
        Room room = existRoomValidate.existRoom(uuid).orElseThrow(IllegalArgumentException::new);
        List<UserEntity> userEntities = userDao.selectByRoom(room.getRoomId());
        Long hostUserId = roomParticipantDao.selectHostUserIdByRoom(room.getRoomId());
        return new RoomIndexBean(room, userEntities, userId, hostUserId);
    }
}
