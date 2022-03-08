package com.okaka.onenightjinroh.application.service.result;

import com.okaka.onenightjinroh.application.domain.GameParticipantsConsideredNightAct;
import com.okaka.onenightjinroh.application.domain.GameParticipantsWithResult;
import com.okaka.onenightjinroh.application.domain.GameResult;
import com.okaka.onenightjinroh.application.domain.Judge;
import com.okaka.onenightjinroh.application.port.HolidayRolesPort;
import com.okaka.onenightjinroh.application.repository.GameParticipantRepository;
import com.okaka.onenightjinroh.application.repository.KaitoNightActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetGameResultUseCase {

    @Autowired
    JudgeFacade judgeFacade;
    @Autowired
    GameParticipantRepository gameParticipantRepository;
    @Autowired
    HolidayRolesPort holidayRolesPort;
    @Autowired
    KaitoNightActRepository kaitoNightActRepository;

    public GameResult invoke(Long gameId, Long gameParticipantId) {
        final var gameParticipants = gameParticipantRepository.findAllByGameIdWithUserAndRole(gameId);

        final var kaitoNightActFormatter = kaitoNightActRepository.findByGameId(gameId);
        final var gameParticipantsConsideredNightAct = new GameParticipantsConsideredNightAct(gameParticipants, kaitoNightActFormatter);

        final var condition = judgeFacade.judge(gameId, kaitoNightActFormatter);
        final var gameParticipantsWithResult = new GameParticipantsWithResult(gameParticipantsConsideredNightAct, condition);

        final var holidayRoles = holidayRolesPort.findByGameId(gameId);
        return new GameResult(gameId, gameParticipantId, new Judge(condition.getResultText()), gameParticipantsWithResult, holidayRoles);
    }
}
