package com.okaka.onenightjinroh.application.repository;

import com.okaka.jinroh.persistence.GameParticipantWithAllDao;
import com.okaka.jinroh.persistence.GameParticipantWithAllEntity;
import com.okaka.jinroh.persistence.GameParticipationDao;
import com.okaka.jinroh.persistence.GameParticipationEntity;
import com.okaka.jinroh.persistence.RoleEntity;
import com.okaka.jinroh.persistence.UserEntity;
import com.okaka.onenightjinroh.application.domain.Game;
import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.GameParticipants;
import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class GameParticipantRepository {

    @Autowired
    GameParticipationDao gameParticipationDao;

    @Autowired
    GameParticipantWithAllDao gameParticipantWithAllDao;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public Optional<GameParticipant> findByParticipantId(Long participantId) {
        final var entity = gameParticipantWithAllDao.selectByParticipantId(participantId);
        return entity.map(it -> new GameParticipant(
                it.getGameParticipationId(),
                new Game(it.getGameId(), null, null, null), // 必要になったら拡張
                new User(it.getUserId(), it.getUserName()),
                Role.byRoleId(it.getRoleId(), it.getRoleName()),
                it.isHostFlg()
        ));
    }

    public List<GameParticipant> findByGameId(Long gameId) {
        return gameParticipantWithAllDao.selectByGameId(gameId).stream()
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

    public GameParticipants findAllByGameIdWithUserAndRole(Long gameId) {
        return GameParticipants.of(findByGameIdWithUserAndRole(gameId));
    }

    public static Map<Long, GameParticipant> toMapGameParticipant(List<GameParticipant> GameParticipants){
        Map<Long, GameParticipant> gameParticipantMap = new HashMap<>();
        GameParticipants.forEach(gameParticipant -> {
            gameParticipantMap.put(gameParticipant.getGameParticipationId(), gameParticipant);
        });
        return gameParticipantMap;
    }

    public static Map<Long, GameParticipationEntity> toMapGameParticipantEntity(List<GameParticipationEntity> gameParticipationEntities){
        Map<Long, GameParticipationEntity> gameParticipantMap = new HashMap<>();
        gameParticipationEntities.forEach(entity -> {
            gameParticipantMap.put(entity.game_participation_id, entity);
        });
        return gameParticipantMap;
    }

    public static GameParticipant toDomainFromEntity(GameParticipantWithAllEntity entity){
        return new GameParticipant(
                entity.getGameParticipationId(),
                new Game(entity.getGameId(), null, null, null), // 必要になったら拡張
                new User(entity.getUserId(), entity.getUserName()),
                Role.byRoleId(entity.getRoleId(), entity.getRoleName()),
                entity.isHostFlg());
    }

    public static GameParticipant toDomainFromEntity(GameParticipationEntity entity, UserEntity userEntity, RoleEntity roleEntity){
        GameParticipant gameParticipant = new GameParticipant(entity.game_participation_id);
        gameParticipant.setHostFlg(entity.host_flg);
        gameParticipant.setGame(Game.Companion.singleCreate(entity.game_id));
        gameParticipant.setRole(RoleRepository.toDomains(roleEntity));
        gameParticipant.setUser(UserRepository.toDomain(userEntity));
        return gameParticipant;
    }
}
