package com.okaka.onenightjinroh.application.service.vote;

import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.GameParticipantRepository;
import com.okaka.onenightjinroh.application.domain.Vote;
import com.okaka.onenightjinroh.application.domain.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IsAllVoteUseCase {
    @Autowired
    VoteRepository voteRepository;

    @Autowired
    GameParticipantRepository gameParticipantRepository;

    public boolean is(Long gameId) {
        List<Long> voteIds = voteRepository.findByGameId(gameId).stream()
                .map(Vote::getGameParticipationId)
                .collect(Collectors.toList());
        return gameParticipantRepository.findByGameId(gameId).stream()
                .map(GameParticipant::getGameParticipationId)
                .allMatch(voteIds::contains);
    }
}
