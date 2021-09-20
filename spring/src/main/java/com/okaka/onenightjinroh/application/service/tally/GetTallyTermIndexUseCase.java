package com.okaka.onenightjinroh.application.service.tally;

import com.okaka.onenightjinroh.application.bean.GameIndexBean;
import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.port.TallyResultPort;
import com.okaka.onenightjinroh.application.repository.GameParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTallyTermIndexUseCase {
    @Autowired
    TallyUseCase tallyUseCase;
    @Autowired
    GameParticipantRepository gameParticipantRepository;
    @Autowired
    TallyResultPort tallyResultPort;

    public TallyTermIndexBean get(Long gameId, Long gameParticipantId) {
        List<GameParticipant> gameParticipants = gameParticipantRepository.findByGameIdWithUserAndRole(gameId);
        GameIndexBean gameIndexBean = GameIndexBean.playerListOnly(gameParticipants, gameParticipantId);
        TallyResultBean tallyResultBean = TallyResultBeanFactory.create(tallyResultPort.searchTallyResults(gameId));

        return new TallyTermIndexBean(gameId, gameIndexBean, tallyResultBean);
    }
}
