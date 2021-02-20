package com.okaka.onenightjinroh.application.service.night;

import com.okaka.jinroh.persistence.GameEntity;
import com.okaka.jinroh.persistence.GameDao;
import com.okaka.jinroh.persistence.GameParticipationEntity;
import com.okaka.jinroh.persistence.GameParticipationDao;
import com.okaka.jinroh.persistence.RoleEntity;
import com.okaka.jinroh.persistence.RoleSelectDao;
import com.okaka.jinroh.persistence.UserEntity;
import com.okaka.jinroh.persistence.UserDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetNightTermIndexUseCase {

    final GameDao gameDao;
    final UserDao userDao;
    final RoleSelectDao roleSelectDao;
    final GameParticipationDao gameParticipationDao;

    public GetNightTermIndexUseCase(GameDao gameDao, UserDao userDao, RoleSelectDao roleSelectDao, GameParticipationDao gameParticipationDao) {
        this.gameDao = gameDao;
        this.userDao = userDao;
        this.roleSelectDao = roleSelectDao;
        this.gameParticipationDao = gameParticipationDao;
    }

    public NightTermIndexBean getNightTermIndex(Long userId, Long roomId, Long gameId) {
        GameEntity gameEntity = gameDao.selectByRoomId(roomId);
        List<RoleEntity> roleEntityList = roleSelectDao.selectRoleListByRuleId(gameEntity.rule_id);
        List<GameParticipationEntity> gameParticipationEntityList = gameParticipationDao.selectGameParticipantsByGameId(gameEntity.game_id);
        List<UserEntity> userEntityList = userDao.selectByGame(gameEntity.game_id);

        return new NightTermIndexBean(userId, roleEntityList, gameParticipationEntityList, userEntityList, gameId);
    }
}
