package com.okaka.onenightjinroh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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

    @Autowired
    HttpSession session;

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

        session.setAttribute("user_id", user.user_id);
        session.setAttribute("room_uuid", room.uuid);

        return 0;
    }

    @RequestMapping(path = "/join-room")
    int joinedRoom(@RequestParam String uuid) {
        Optional<Room> optRoom = roomDao.selectRoomByUUID(uuid);
        if (optRoom.isPresent() == false) {
            throw new IllegalArgumentException();
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

        session.setAttribute("user_id", user.user_id);
        session.setAttribute("room_uuid", optRoom.get().uuid);

        return 0;
    }
}
