package com.okaka.onenightjinroh.api;

import com.okaka.jinroh.persistence.GameDao;
import com.okaka.jinroh.persistence.GameParticipation;
import com.okaka.jinroh.persistence.GameParticipationDao;
import com.okaka.jinroh.persistence.Role;
import com.okaka.jinroh.persistence.RoleSelectDao;
import com.okaka.jinroh.persistence.Room;
import com.okaka.jinroh.persistence.RoomDao;
import com.okaka.jinroh.persistence.User;
import com.okaka.jinroh.persistence.UserDao;
import com.okaka.jinroh.persistence.Game;
import com.okaka.onenightjinroh.GameBean;
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
