package com.okaka.onenightjinroh.application.service.tally;

import com.okaka.onenightjinroh.application.domain.GameIndexBean;
import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.GameParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTallyTermIndexUseCase {
    @Autowired
    TallyUseCase tallyUseCase;
    @Autowired
    GameParticipantRepository gameParticipantRepository;

    public TallyTermIndexBean get(Long gameId, Long gameParticipantId) {
        List<GameParticipant> gameParticipants = gameParticipantRepository.findByGameIdWithUserAndRole(gameId);
        GameIndexBean gameIndex = new GameIndexBean(gameParticipants, gameParticipantId);
        TallyResultBean tallyResult = tallyUseCase.tally(gameId);

        return new TallyTermIndexBean(gameId, gameIndex, tallyResult);
    }
}
