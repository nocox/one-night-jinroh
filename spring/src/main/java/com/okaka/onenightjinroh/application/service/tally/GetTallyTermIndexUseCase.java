package com.okaka.onenightjinroh.application.service.tally;

import com.okaka.onenightjinroh.application.bean.GameIndexBean;
import com.okaka.onenightjinroh.application.bean.GameParticipantBean;
import com.okaka.onenightjinroh.application.domain.GameParticipants;
import com.okaka.onenightjinroh.application.domain.ParticipantDisplayChecker;
import com.okaka.onenightjinroh.application.domain.RoleNightActFormatter;
import com.okaka.onenightjinroh.application.port.TallyResultPort;
import com.okaka.onenightjinroh.application.repository.GameParticipantRepository;
import com.okaka.onenightjinroh.application.repository.RoleNightActFormatterRepository;
import com.okaka.onenightjinroh.application.service.talk.CoState;
import com.okaka.onenightjinroh.application.service.talk.CoStateBean;
import com.okaka.onenightjinroh.application.service.talk.CoStateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetTallyTermIndexUseCase {
    @Autowired
    TallyUseCase tallyUseCase;
    @Autowired
    GameParticipantRepository gameParticipantRepository;
    @Autowired
    RoleNightActFormatterRepository roleNightActFormatterRepository;
    @Autowired
    TallyResultPort tallyResultPort;
    @Autowired
    CoStateFactory coStateFactory;

    public TallyTermIndexBean get(Long gameId, Long gameParticipantId) {
        GameParticipants gameParticipants = GameParticipants.of(gameParticipantRepository.findByGameIdWithUserAndRole(gameId));
        RoleNightActFormatter roleNightActFormatter = roleNightActFormatterRepository.fetchNightAct(gameId, gameParticipantId);
        ParticipantDisplayChecker displayChecker = ParticipantDisplayChecker.of(gameParticipants.mySelf(gameParticipantId), roleNightActFormatter);

        List<GameParticipantBean> participantBeans = gameParticipants.stream()
                .map(displayChecker::check)
                .collect(Collectors.toList());

        GameIndexBean gameIndexBean = GameIndexBean.of(participantBeans, gameParticipantId, null);
        
        TallyResultBean tallyResultBean = TallyResultBeanFactory.create(tallyResultPort.searchTallyResults(gameId));

        CoState coState = coStateFactory.create(gameId);
        CoStateBean coBean = CoStateBean.fromDomain(coState);

        return new TallyTermIndexBean(gameId, gameIndexBean, tallyResultBean, coBean.getCoBeans());
    }
}
