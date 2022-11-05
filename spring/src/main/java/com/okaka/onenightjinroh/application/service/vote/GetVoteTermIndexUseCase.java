package com.okaka.onenightjinroh.application.service.vote;

import com.okaka.onenightjinroh.application.bean.GameIndexBean;
import com.okaka.onenightjinroh.application.bean.GameParticipantBean;
import com.okaka.onenightjinroh.application.domain.CoState;
import com.okaka.onenightjinroh.application.domain.CoStateFactory;
import com.okaka.onenightjinroh.application.domain.GameParticipants;
import com.okaka.onenightjinroh.application.domain.ParticipantDisplayChecker;
import com.okaka.onenightjinroh.application.domain.RoleNightActFormatter;
import com.okaka.onenightjinroh.application.repository.GameParticipantRepository;
import com.okaka.onenightjinroh.application.repository.RoleNightActFormatterRepository;
import com.okaka.onenightjinroh.application.repository.VoteRepository;
import com.okaka.onenightjinroh.application.service.talk.CoStateBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GetVoteTermIndexUseCase {
    @Autowired
    GameParticipantRepository gameParticipantRepository;
    @Autowired
    RoleNightActFormatterRepository roleNightActFormatterRepository;
    @Autowired
    CoStateFactory coStateFactory;
    @Autowired
    VoteRepository voteRepository;

    public VoteTermIndexBean get(Long gameId, Long gameParticipantId) {
        GameParticipants gameParticipants = GameParticipants.of(gameParticipantRepository.findByGameIdWithUserAndRole(gameId));
        Optional<? extends RoleNightActFormatter> roleNightActFormatter = roleNightActFormatterRepository.fetchNightAct(gameId, gameParticipantId);
        ParticipantDisplayChecker displayChecker = ParticipantDisplayChecker.of(gameParticipants.mySelf(gameParticipantId), roleNightActFormatter.orElse(null));

        List<GameParticipantBean> participantBeans = gameParticipants.stream()
                .map(displayChecker::check)
                .collect(Collectors.toList());

        String nightActLog = roleNightActFormatterRepository.fetchNightAct(gameId, gameParticipantId).map(it->it.toActLog()).orElse("");
        GameIndexBean gameIndex = GameIndexBean.of(participantBeans, gameParticipantId, nightActLog);

        List<GameParticipantBean> canVotePlayers = gameParticipants.stream()
                .filter(participant -> !participant.getGameParticipationId().equals(gameParticipantId))
                .map(GameParticipantBean::new)
                .collect(Collectors.toList());
        Long votingDestination = voteRepository.findByVoterId(gameParticipantId)
                .map(vote -> vote.toGameParticipationId)
                .orElse(null);
        VoteIndexBean voteIndexBean = new VoteIndexBean(canVotePlayers, votingDestination);

        CoState coState = coStateFactory.create(gameId);
        CoStateBean coBean = CoStateBean.fromDomain(coState);

        return new VoteTermIndexBean(gameId, gameIndex, voteIndexBean, coBean.getCoBeans());
    }
}
