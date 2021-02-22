package com.okaka.onenightjinroh.application.domain;

import com.okaka.jinroh.persistence.VoteDao;
import com.okaka.jinroh.persistence.VoteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VoteRepository {
    @Autowired
    VoteDao voteDao;

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
}
