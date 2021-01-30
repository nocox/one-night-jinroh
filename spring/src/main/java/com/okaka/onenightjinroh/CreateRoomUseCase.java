package com.okaka.onenightjinroh;

import com.okaka.jinroh.persistence.Room;
import com.okaka.jinroh.persistence.RoomDao;
import com.okaka.jinroh.persistence.RoomParticipant;
import com.okaka.jinroh.persistence.RoomParticipantDao;
import com.okaka.jinroh.persistence.User;
import com.okaka.jinroh.persistence.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CreateRoomUseCase {
    private final RoomDao roomDao;
    private final UserDao userDao;
    private final RoomParticipantDao roomParticipantDao;

    public CreateRoomUseCase(RoomDao roomDao, UserDao userDao, RoomParticipantDao roomParticipantDao) {
        this.roomDao = roomDao;
        this.userDao = userDao;
        this.roomParticipantDao = roomParticipantDao;
    }

    @Transactional
    public CreateRoomUseCaseDto createRoom(){
        Room room = new Room();
        room.uuid = UUID.randomUUID().toString();
        room.rule_id = null;
        roomDao.insert(room);

        User user = new User();
        user.user_name = "ホスト";
        userDao.insert(user);

        RoomParticipant roomParticipant = new RoomParticipant();
        roomParticipant.room_id = room.room_id;
        roomParticipant.user_id = user.user_id;
        roomParticipant.host_flg = true;
        roomParticipantDao.insert(roomParticipant);

        return new CreateRoomUseCaseDto(user, room);
    }
}
