package com.okaka.onenightjinroh.application.service.talk;

import com.okaka.onenightjinroh.application.bean.GameIndexBean;
import com.okaka.onenightjinroh.application.bean.GameParticipantBean;
import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.ParticipantDisplayChecker;
import com.okaka.onenightjinroh.application.domain.RoleNightActFormatter;
import com.okaka.onenightjinroh.application.repository.GameParticipantRepository;
import com.okaka.onenightjinroh.application.repository.RoleNightActFormatterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetTalkTermIndexUseCase {

    @Autowired
    GameParticipantRepository gameParticipantRepository;
    @Autowired
    RoleNightActFormatterRepository roleNightActFormatterRepository;

    public TalkTermIndexBean get(Long gameId, Long gameParticipantId) {

        List<GameParticipant> gameParticipants = gameParticipantRepository.findByGameIdWithUserAndRole(gameId);

        List<RoleNightActFormatter> roleNightActs = gameParticipants.stream()
                .map(GameParticipant::getGameParticipationId)
                .filter(id -> id.equals(gameParticipantId))
                .map(id -> roleNightActFormatterRepository.fetchNightAct(gameId, id))
                .collect(Collectors.toList());

        GameParticipant mySelf = gameParticipants.stream()
                .filter(domain -> domain.getGameParticipationId().equals(gameParticipantId))
                .findFirst()
                .orElseThrow();

        ParticipantDisplayChecker displayChecker = ParticipantDisplayChecker.of(mySelf, roleNightActs);

        List<GameParticipantBean> participantBeans = gameParticipants.stream()
                .map(displayChecker::check)
                .collect(Collectors.toList());

        String myNightActLog = roleNightActFormatterRepository.fetchNightAct(gameId, gameParticipantId).toActLog();
        GameIndexBean gameIndex = GameIndexBean.of(participantBeans, gameParticipantId, myNightActLog);
        return new TalkTermIndexBean(gameIndex, gameId);
    }
}
