package com.okaka.onenightjinroh.application.service.night;

import com.okaka.jinroh.persistence.Game;
import com.okaka.jinroh.persistence.GameDao;
import com.okaka.jinroh.persistence.GameParticipation;
import com.okaka.jinroh.persistence.GameParticipationDao;
import com.okaka.jinroh.persistence.Role;
import com.okaka.jinroh.persistence.RoleSelectDao;
import com.okaka.jinroh.persistence.User;
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

    public NightTermIndexBean getNightTermIndex(Long userId, Long roomId) {
        Game game = gameDao.selectByRoomId(roomId);
        List<Role> roleList = roleSelectDao.selectRoleListByRuleId(game.rule_id);
        List<GameParticipation> gameParticipationList = gameParticipationDao.selectGameParticipantsByGameId(game.game_id);
        List<User> userList = userDao.selectByGame(game.game_id);

        return new NightTermIndexBean(userId, roleList, gameParticipationList, userList);
    }
}
