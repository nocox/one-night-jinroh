package com.okaka.onenightjinroh.application.service.tally;

import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.GameParticipantRepository;
import com.okaka.onenightjinroh.application.domain.Vote;
import com.okaka.onenightjinroh.application.domain.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TallyUseCase {
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    GameParticipantRepository gameParticipantRepository;

    public TallyResultBean tally(Long gameId) {
        List<Vote> voteList = voteRepository.findByGameId(gameId);
        List<GameParticipant> gameParticipantList = gameParticipantRepository.findByGameIdWithUserAndRole(gameId);

        return TallyResultBeanFactory.create(voteList, gameParticipantList);
    }
}
