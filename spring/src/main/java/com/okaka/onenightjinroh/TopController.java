package com.okaka.onenightjinroh;

import com.okaka.jinroh.persistence.Room;
import com.okaka.jinroh.persistence.RoomDao;
import com.okaka.jinroh.persistence.RoomParticipant;
import com.okaka.jinroh.persistence.User;
import com.okaka.jinroh.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.okaka.jinroh.persistence.RoomParticipantDao;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class TopController {

    @Autowired
    RoomDao roomDao;

    @Autowired
    UserDao userDao;

    @Autowired
    RoomParticipantDao roomParticipantDao;

    @Autowired
    CreateRoomUseCase createRoomUseCase;

    @RequestMapping(path = "/create-room")
    @CrossOrigin(origins = {"http://localhost:8081"}, allowCredentials = "true")
    int createRoom(HttpSession session) {
        CreateRoomUseCaseDto dto = createRoomUseCase.createRoom();
        session.setAttribute("user_id", dto.getUser().user_id);
        session.setAttribute("room_uuid", dto.getRoom().uuid);
        return 0;
    }

    @RequestMapping(path = "/join-room")
    @CrossOrigin(origins = {"http://localhost:8081"}, allowCredentials = "true")
    int joinedRoom(@RequestParam String uuid, HttpSession session) {
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
