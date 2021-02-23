package com.okaka.onenightjinroh.application.domain;

import com.okaka.jinroh.persistence.VoteDao;
import com.okaka.jinroh.persistence.VoteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class VoteRepository {
    @Autowired
    VoteDao voteDao;

    public List<Vote> findByGameId(Long gameId) {
        return voteDao.selectByGame(gameId).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public void createVote(Vote vote) {
        voteDao.insert(toEntity(vote));
    }

    public VoteEntity toEntity(Vote vote){
        VoteEntity voteEntity = new VoteEntity();
        voteEntity.game_participation_id = vote.getGameParticipationId();
        voteEntity.to_game_participation_id = vote.toGameParticipationId;
        voteEntity.peace_village_flg = false;
        return voteEntity;
    }

    public Vote toDomain(VoteEntity entity){
        Vote vote = new Vote(entity.vote_id);
        vote.setGameParticipationId(entity.game_participation_id);
        vote.setToGameParticipationId(entity.to_game_participation_id);
        vote.setPeaceVillageFlg(entity.peace_village_flg);
        return vote;
    }
}
