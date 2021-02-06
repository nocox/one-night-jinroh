package com.okaka.onenightjinroh.application.service.room;

import com.okaka.jinroh.persistence.Game;
import com.okaka.jinroh.persistence.GameDao;
import com.okaka.jinroh.persistence.GameParticipation;
import com.okaka.jinroh.persistence.GameParticipationDao;
import com.okaka.jinroh.persistence.Role;
import com.okaka.jinroh.persistence.RoleSelectDao;
import com.okaka.jinroh.persistence.RoomParticipantDao;
import com.okaka.jinroh.persistence.User;
import com.okaka.jinroh.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StartGameUseCase {

    static final Map<Integer, Long> RULE_MAP = new HashMap<>() {
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

    @Autowired
    private GameDao gameDao;

    @Autowired
    private RoleSelectDao roleSelectDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private GameParticipationDao gameParticipationDao;

    @Autowired
    private RoomParticipantDao roomParticipantDao;


    public GameStartWebSocketBean startGame(Long roomId, Long hostUserId) {
        int participantCount = roomParticipantDao.selectParticipantCount(roomId);

        Game game = new Game();
        game.room_id = roomId;
        game.rule_id = RULE_MAP.get(participantCount);
        gameDao.insert(game);

        List<Role> roleList = roleSelectDao.selectRoleListByRuleId(game.rule_id);
        Collections.shuffle(roleList);
        List<User> userList = userDao.selectByRoom(roomId);

        for (int i = 0; i < userList.size(); i ++) {
            GameParticipation gameParticipation = new GameParticipation();
            gameParticipation.game_id = game.game_id;
            gameParticipation.user_id = userList.get(i).user_id;
            gameParticipation.host_flg = userList.get(i).user_id.equals(hostUserId);
            gameParticipation.role_id = roleList.get(i).role_id;
            gameParticipationDao.insert(gameParticipation);
        }

        return new GameStartWebSocketBean(
                roleSelectDao.selectRoleListByRuleId(game.rule_id),
                userList.size(),
                game.game_id
        );
    }
}
