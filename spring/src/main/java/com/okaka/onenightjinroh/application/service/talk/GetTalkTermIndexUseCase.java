package com.okaka.onenightjinroh.application.service.talk;

import com.okaka.onenightjinroh.application.bean.GameIndexBean;
import com.okaka.onenightjinroh.application.bean.GameParticipantBean;
import com.okaka.onenightjinroh.application.domain.CoState;
import com.okaka.onenightjinroh.application.domain.CoStateFactory;
import com.okaka.onenightjinroh.application.domain.GameParticipants;
import com.okaka.onenightjinroh.application.domain.ParticipantDisplayChecker;
import com.okaka.onenightjinroh.application.domain.RoleNightActFormatter;
import com.okaka.onenightjinroh.application.repository.GameParticipantRepository;
import com.okaka.onenightjinroh.application.repository.RoleNightActFormatterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GetTalkTermIndexUseCase {

    @Autowired
    GameParticipantRepository gameParticipantRepository;
    @Autowired
    RoleNightActFormatterRepository roleNightActFormatterRepository;
    @Autowired
    CoStateFactory coStateFactory;

    public TalkTermIndexBean get(Long gameId, Long gameParticipantId) {

        GameParticipants gameParticipants = GameParticipants.of(gameParticipantRepository.findByGameIdWithUserAndRole(gameId));
        Optional<? extends RoleNightActFormatter> roleNightActFormatter = roleNightActFormatterRepository.fetchNightAct(gameId, gameParticipantId);
        ParticipantDisplayChecker displayChecker = ParticipantDisplayChecker.of(gameParticipants.mySelf(gameParticipantId), roleNightActFormatter.orElse(null));

        List<GameParticipantBean> participantBeans = gameParticipants.stream()
                .map(displayChecker::check)
                .collect(Collectors.toList());

        String myNightActLog = roleNightActFormatter.map(it->it.toActLog()).orElse("");
        GameIndexBean gameIndexBean = GameIndexBean.of(participantBeans, gameParticipantId, myNightActLog);

        CoState coState = coStateFactory.create(gameId);
        CoStateBean coBean = CoStateBean.fromDomain(coState);

        return new TalkTermIndexBean(gameId, gameIndexBean, coBean.getCoBeans());
    }
}
