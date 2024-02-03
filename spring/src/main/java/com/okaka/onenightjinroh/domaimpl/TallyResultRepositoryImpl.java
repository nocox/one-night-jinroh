package com.okaka.onenightjinroh.domaimpl;

import com.okaka.jinroh.persistence.GameParticipationDao;
import com.okaka.jinroh.persistence.GameParticipationEntity;
import com.okaka.jinroh.persistence.GameVoteTallyDao;
import com.okaka.jinroh.persistence.GameVoteTallyEntity;
import com.okaka.jinroh.persistence.RoleEntity;
import com.okaka.jinroh.persistence.UserEntity;
import com.okaka.onenightjinroh.application.domain.Game;
import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.TallyResult;
import com.okaka.onenightjinroh.application.repository.TallyResultRepository;
import com.okaka.onenightjinroh.application.repository.GameParticipantRepository;
import com.okaka.onenightjinroh.application.repository.RoleRepository;
import com.okaka.onenightjinroh.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TallyResultRepositoryImpl implements TallyResultRepository {
    @Autowired
    GameVoteTallyDao gameVoteTallyDao;
    @Autowired
    GameParticipationDao gameParticipationDao;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public TallyResult load(Long gameParticipationId) {
        // DaoとMapperを使って，Domainを取得する．
        return null;
    }

    @Override
    public List<TallyResult> searchTallyResults(Long gameId) {
        List<GameVoteTallyEntity> gameVoteTallyEntities = gameVoteTallyDao.selectByGameId(gameId);
        List<GameParticipationEntity> gameParticipationEntities = gameParticipationDao.selectGameParticipantsByGameId(gameId);
        Map<Long, GameParticipationEntity> participantMap = GameParticipantRepository.toMapGameParticipantEntity(gameParticipationEntities);
        Map<Long, UserEntity> usersMap = userRepository.toMapUserEntitiesByGameId(gameId);
        Map<Long, RoleEntity> rolesMap = roleRepository.toMapRoleEntities();

        return gameVoteTallyEntities.stream().map(gameVoteTallyEntity -> {
            Long gameParticipationId = gameVoteTallyEntity.game_participation_id;
            GameParticipationEntity gameParticipationEntity = participantMap.get(gameParticipationId);
            return mapToDomain(
                    gameVoteTallyEntity,
                    gameParticipationEntity,
                    usersMap.get(gameParticipationEntity.user_id),
                    rolesMap.get(gameParticipationEntity.role_id)
            );
        }).collect(Collectors.toList());
    }

    @Override
    public void saveGameVoteTally(TallyResult tallyResult) {
        GameVoteTallyEntity gameVoteTallyEntity = new GameVoteTallyEntity();
        gameVoteTallyEntity.game_id = tallyResult.getGame().getGameId();
        gameVoteTallyEntity.game_participation_id = tallyResult.getGameParticipant().getGameParticipationId();
        gameVoteTallyEntity.vote_count = tallyResult.getVoteCount();
        gameVoteTallyEntity.selected = tallyResult.getSelected();
        gameVoteTallyDao.insert(gameVoteTallyEntity);
    }

    private TallyResult mapToDomain(GameVoteTallyEntity gameVoteTallyEntity, GameParticipationEntity gameParticipationEntity, UserEntity userEntity, RoleEntity roleEntity) {
        Game game = new Game(gameVoteTallyEntity.game_id);
        GameParticipant gameParticipant = GameParticipantRepository.toDomainFromEntity(gameParticipationEntity, userEntity, roleEntity);
        Integer voteCount = gameVoteTallyEntity.vote_count;
        boolean selected = gameVoteTallyEntity.selected;

        return new TallyResult(game, gameParticipant, voteCount, selected);
    }
}
