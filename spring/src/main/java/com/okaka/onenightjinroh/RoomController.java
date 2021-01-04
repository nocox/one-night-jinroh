package com.okaka.onenightjinroh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
public class RoomController {

    @Autowired
    HttpSession session;

    @Autowired
    UserDao userDao;

    @Autowired
    RoomParticipantDao roomParticipantDao;

    @Autowired
    RoomDao roomDao;

    @RequestMapping(path = "/room-index")
    @CrossOrigin(origins = {"http://localhost:8081"}, allowCredentials = "true")
    RoomBean getRoom() {
        String uuid = session.getAttribute("room_uuid").toString();
        String strUserId = session.getAttribute("user_id").toString();
        Long userId = Long.valueOf(strUserId);

        Optional<Room> optRoom = roomDao.selectRoomByUUID(uuid);

        if (optRoom.isPresent() == false) {
            throw new IllegalArgumentException();
        }

        List<User> users = userDao.selectByRoom(optRoom.get().room_id);
        Long hostUserId = roomParticipantDao.selectHostUserIdByRoom(optRoom.get().room_id);
        boolean hostFlg = (hostUserId.equals(userId));

        return new RoomBean(optRoom.get(), users, hostFlg, hostUserId);
    }
}
