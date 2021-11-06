package com.okaka.onenightjinroh.application.service.vote;

import com.okaka.onenightjinroh.application.bean.GameIndexBean;
import com.okaka.onenightjinroh.application.bean.GameParticipantBean;
import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.repository.GameParticipantRepository;
import com.okaka.onenightjinroh.application.repository.RoleNightActFormatterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetVoteTermIndexUseCase {
    @Autowired
    GameParticipantRepository gameParticipantRepository;
    @Autowired
    RoleNightActFormatterRepository roleNightActFormatterRepository;

    public VoteTermIndexBean get(Long gameId, Long gameParticipantId) {
        List<GameParticipant> gameParticipants = gameParticipantRepository.findByGameIdWithUserAndRole(gameId);
        String nightActLog = roleNightActFormatterRepository.fetchNightAct(gameId, gameParticipantId).toActLog();
        GameIndexBean gameIndex = GameIndexBean.withRoleActLog(gameParticipants, gameParticipantId, nightActLog);

        List<GameParticipantBean> canVotePlayers = gameParticipants.stream()
                .filter(participant -> !participant.getGameParticipationId().equals(gameParticipantId))
                .map(GameParticipantBean::new)
                .collect(Collectors.toList());
        VoteIndexBean voteIndexBean = new VoteIndexBean(canVotePlayers);

        return new VoteTermIndexBean(gameId, gameIndex, voteIndexBean);
    }
}
