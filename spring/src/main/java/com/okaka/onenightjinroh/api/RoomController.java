package com.okaka.onenightjinroh.api;

import com.okaka.jinroh.persistence.Game;
import com.okaka.jinroh.persistence.GameDao;
import com.okaka.jinroh.persistence.GameParticipation;
import com.okaka.jinroh.persistence.GameParticipationDao;
import com.okaka.jinroh.persistence.Role;
import com.okaka.jinroh.persistence.RoleSelectDao;
import com.okaka.jinroh.persistence.Room;
import com.okaka.jinroh.persistence.RoomDao;
import com.okaka.jinroh.persistence.RoomParticipantDao;
import com.okaka.jinroh.persistence.User;
import com.okaka.jinroh.persistence.UserDao;
import com.okaka.onenightjinroh.GameStartBean;
import com.okaka.onenightjinroh.RoomBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Autowired
    GameDao gameDao;

    @Autowired
    RoleSelectDao roleSelectDao;

    @Autowired
    GameParticipationDao gameParticipationDao;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

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

    @RequestMapping(path = "/game-start")
    @CrossOrigin(origins = {"http://localhost:8081"}, allowCredentials = "true")
    int startGame() {
        String uuid = session.getAttribute("room_uuid").toString();
        Optional<Room> optRoom = roomDao.selectRoomByUUID(uuid);
        if (optRoom.isPresent() == false) {
            throw new IllegalArgumentException();
        }
        Room room = optRoom.get();

        Long userId = Long.valueOf(session.getAttribute("user_id").toString());
        Long hostUserId = roomParticipantDao.selectHostUserIdByRoom(optRoom.get().room_id);
        if (userId.equals(hostUserId) == false) {
            throw new IllegalArgumentException();
        }

        int participantCount = roomParticipantDao.selectParticipantCount(room.room_id);
        if (participantCount < 3) {
            throw new IllegalArgumentException();
        }

        Game game = new Game();
        game.room_id = room.room_id;
        game.rule_id = RULE_MAP.get(participantCount);
        gameDao.insert(game);

        List<Role> roleList = roleSelectDao.selectRoleListByRuleId(game.rule_id);
        Collections.shuffle(roleList);
        List<User> userList = userDao.selectByRoom(room.room_id);

        for (int i = 0; i < userList.size(); i ++) {
            GameParticipation gameParticipation = new GameParticipation();
            gameParticipation.game_id = game.game_id;
            gameParticipation.user_id = userList.get(i).user_id;
            gameParticipation.host_flg = userList.get(i).user_id.equals(hostUserId);
            gameParticipation.role_id = roleList.get(i).role_id;
            gameParticipationDao.insert(gameParticipation);
        }

        // ここでブロードキャストする
        GameStartBean gameStartBean = new GameStartBean(
                roleSelectDao.selectRoleListByRuleId(game.rule_id),
                userList.size(),
                game.game_id
        );
        this.messagingTemplate.convertAndSend("/topic/" + room.uuid, gameStartBean);
        return 0;
    }

    Map<Integer, Long> RULE_MAP = new HashMap<>() {
        {
            put(3, 1L);
            put(4, 2L);
            put(5, 3L);
            put(6, 4L);
            put(7, 5L);
            put(8, 6L);
            put(9, 7L);
            put(10, 8L);
        }
    };
}
