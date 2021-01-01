package com.okaka.onenightjinroh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
public class TopController {

    @Autowired
    RoomDao roomDao;

    @Autowired
    UserDao userDao;

    @Autowired
    RoomParticipantDao roomParticipantDao;

    @RequestMapping(path = "/create-room")
    int createRoom() {
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
        return 0;
    }

    @RequestMapping(path = "/join-room")
    int joinedRoom(@RequestParam String uuid) {
        Optional<Room> optRoom = roomDao.selectRoomByUUID(uuid);
        if (optRoom.isPresent() == false) {
            return 2;
        }

        int userCount = userDao.selectCount();
        User user = new User();
        user.user_name = "プレイヤー" + (userCount + 1);
        userDao.insert(user);

        RoomParticipant roomParticipant = new RoomParticipant();
        roomParticipant.room_id = optRoom.get().room_id;
        roomParticipant.user_id = user.user_id;
        roomParticipant.host_flg = false;
        roomParticipantDao.insert(roomParticipant);
        return 0;
    }
}
