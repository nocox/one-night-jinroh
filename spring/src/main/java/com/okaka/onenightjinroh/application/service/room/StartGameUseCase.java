package com.okaka.onenightjinroh.application.service.room;

import com.okaka.jinroh.persistence.GameEntity;
import com.okaka.jinroh.persistence.GameDao;
import com.okaka.jinroh.persistence.GameParticipationEntity;
import com.okaka.jinroh.persistence.GameParticipationDao;
import com.okaka.jinroh.persistence.RoleEntity;
import com.okaka.jinroh.persistence.RoleSelectDao;
import com.okaka.jinroh.persistence.RoomParticipantDao;
import com.okaka.jinroh.persistence.UserEntity;
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

        GameEntity gameEntity = new GameEntity();
        gameEntity.room_id = roomId;
        gameEntity.rule_id = RULE_MAP.get(participantCount);
        gameDao.insert(gameEntity);

        List<RoleEntity> roleEntityList = roleSelectDao.selectRoleListByRuleId(gameEntity.rule_id);
        Collections.shuffle(roleEntityList);
        List<UserEntity> userEntityList = userDao.selectByRoom(roomId);

        for (int i = 0; i < userEntityList.size(); i ++) {
            GameParticipationEntity gameParticipationEntity = new GameParticipationEntity();
            gameParticipationEntity.game_id = gameEntity.game_id;
            gameParticipationEntity.user_id = userEntityList.get(i).user_id;
            gameParticipationEntity.host_flg = userEntityList.get(i).user_id.equals(hostUserId);
            gameParticipationEntity.role_id = roleEntityList.get(i).role_id;
            gameParticipationDao.insert(gameParticipationEntity);
        }

        return new GameStartWebSocketBean(
                roleSelectDao.selectRoleListByRuleId(gameEntity.rule_id),
                userEntityList.size(),
                gameEntity.game_id
        );
    }
}
