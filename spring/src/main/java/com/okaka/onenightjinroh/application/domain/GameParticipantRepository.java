package com.okaka.onenightjinroh.application.domain;

import com.okaka.jinroh.persistence.GameDao;
import com.okaka.jinroh.persistence.GameParticipationDao;
import com.okaka.jinroh.persistence.GameParticipationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class GameParticipantRepository {

    @Autowired
    GameParticipationDao gameParticipationDao;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public List<GameParticipant> findByGameId(Long gameId) {
        return gameParticipationDao.selectGameParticipantsByGameId(gameId).stream()
                .map(GameParticipantRepository::toDomainFromEntity)
                .collect(Collectors.toList());
    }

    public List<GameParticipant> findByGameIdWithUserAndRole(Long gameId) {
        List<GameParticipant> gps = findByGameId(gameId);
        Map<Long, User> usersMap = userRepository.toMapUsersByGameId(gameId);
        Map<Long, Role> rolesMap = roleRepository.toMapRoles();

        gps.forEach(gameParticipant -> {
            Long userId = gameParticipant.getUser().getUserId();
            gameParticipant.setUser(usersMap.get(userId));
            Long roleId = gameParticipant.getRole().getRoleId();
            gameParticipant.setRole(rolesMap.get(roleId));
        });
        return gps;
    }

    public static Map<Long, GameParticipant> toMapGameParticipant(List<GameParticipationEntity> gameParticipationEntities){
        Map<Long, GameParticipant> gameParticipantMap = new HashMap<>();
        gameParticipationEntities.forEach(entity -> {
            GameParticipant gameParticipant = toDomainFromEntity(entity);
            gameParticipantMap.put(entity.game_participation_id, gameParticipant);
        });
        return gameParticipantMap;
    }

    private static GameParticipant toDomainFromEntity(GameParticipationEntity entity){
        GameParticipant gameParticipant = new GameParticipant(entity.game_participation_id);
        gameParticipant.setHostFlg(entity.host_flg);
        gameParticipant.setGame(new Game(entity.game_id));
        gameParticipant.setRole(new Role(entity.role_id));
        gameParticipant.setUser(new User(entity.user_id));
        return gameParticipant;
    }
}
