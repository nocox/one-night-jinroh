package com.okaka.onenightjinroh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
public class NightController {
    @Autowired
    HttpSession session;

    @Autowired
    UserDao userDao;

    @Autowired
    RoomDao roomDao;

    @Autowired
    GameDao gameDao;

    @Autowired
    GameParticipationDao gameParticipationDao;

    @Autowired
    RoleSelectDao roleSelectDao;


    @RequestMapping(path = "/night-index")
    @CrossOrigin(origins = {"http://localhost:8081"}, allowCredentials = "true")
    GameBean getRoom() {
        String uuid = session.getAttribute("room_uuid").toString();
        String strUserId = session.getAttribute("user_id").toString();
        Long userId = Long.valueOf(strUserId);

        Optional<Room> optRoom = roomDao.selectRoomByUUID(uuid);

        if (optRoom.isPresent() == false) {
            throw new IllegalArgumentException();
        }

        Game game = gameDao.selectByRoomId(optRoom.get().room_id);
        List<Role> roleList = roleSelectDao.selectRoleListByRuleId(game.rule_id);
        List<GameParticipation> gameParticipationList = gameParticipationDao.selectGameParticipationByGameId(game.game_id);
        List<User> userList = userDao.selectByGame(game.game_id);

        return new GameBean(userId, roleList, gameParticipationList, userList);
    }
}
