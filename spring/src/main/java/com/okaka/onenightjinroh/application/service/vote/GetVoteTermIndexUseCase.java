package com.okaka.onenightjinroh.application.service.vote;

import com.okaka.onenightjinroh.application.bean.GameIndexBean;
import com.okaka.onenightjinroh.application.bean.GameParticipantBean;
import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.repository.GameParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetVoteTermIndexUseCase {
    @Autowired
    GameParticipantRepository gameParticipantRepository;

    public VoteTermIndexBean get(Long gameId, Long gameParticipantId) {
        List<GameParticipant> gameParticipants = gameParticipantRepository.findByGameIdWithUserAndRole(gameId);
        GameIndexBean gameIndex = new GameIndexBean(gameParticipants, gameParticipantId);

        List<GameParticipantBean> canVotePlayers = gameParticipants.stream()
                .filter(participant -> participant.getGameParticipationId().equals(gameParticipantId) == false)
                .map(GameParticipantBean::new)
                .collect(Collectors.toList());
        VoteIndexBean voteIndexBean = new VoteIndexBean(canVotePlayers);

        return new VoteTermIndexBean(gameId, gameIndex, voteIndexBean);
    }
}
