package com.okaka.onenightjinroh.application.service.room;

import com.okaka.jinroh.persistence.Room;
import com.okaka.jinroh.persistence.RoomParticipantDao;
import com.okaka.jinroh.persistence.User;
import com.okaka.jinroh.persistence.UserDao;
import com.okaka.onenightjinroh.application.domain.ExistRoomValidate;
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
        List<User> users = userDao.selectByRoom(room.room_id);
        Long hostUserId = roomParticipantDao.selectHostUserIdByRoom(room.room_id);
        return new RoomIndexBean(room, users, userId, hostUserId);
    }
}
