package com.okaka.onenightjinroh.adapter;

import com.okaka.jinroh.persistence.GameParticipationEntity;
import com.okaka.jinroh.persistence.GameVoteTallyEntity;
import com.okaka.jinroh.persistence.RoleEntity;
import com.okaka.jinroh.persistence.UserEntity;
import com.okaka.onenightjinroh.application.domain.Game;
import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.GameParticipantRepository;
import com.okaka.onenightjinroh.application.domain.TallyResult;

public class TallyResultMapper {

    public static TallyResult mapToDomain(GameVoteTallyEntity gameVoteTallyEntity, GameParticipationEntity gameParticipationEntity, UserEntity userEntity, RoleEntity roleEntity) {
        Game game = new Game(gameVoteTallyEntity.game_id);
        GameParticipant gameParticipant = GameParticipantRepository.toDomainFromEntity(gameParticipationEntity, userEntity, roleEntity);
        Integer voteCount = gameVoteTallyEntity.vote_count;
        boolean selected = gameVoteTallyEntity.selected;

        return new TallyResult(game, gameParticipant, voteCount, selected);
    }

    public static GameVoteTallyEntity mapToDomaEntity(TallyResult tallyResult) {
        GameVoteTallyEntity gameVoteTallyEntity = new GameVoteTallyEntity();
        gameVoteTallyEntity.game_id = tallyResult.getGame().getGameId();
        gameVoteTallyEntity.game_participation_id = tallyResult.getGameParticipant().getGameParticipationId();
        gameVoteTallyEntity.vote_count = tallyResult.getVoteCount();
        gameVoteTallyEntity.selected = tallyResult.getSelected();
        return gameVoteTallyEntity;
    }
}
