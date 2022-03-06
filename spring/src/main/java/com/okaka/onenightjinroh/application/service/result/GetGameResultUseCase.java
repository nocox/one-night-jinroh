package com.okaka.onenightjinroh.application.service.result;

import com.okaka.onenightjinroh.application.domain.GameParticipants;
import com.okaka.onenightjinroh.application.domain.GameParticipantsConsideredNightAct;
import com.okaka.onenightjinroh.application.domain.GameParticipantsWithResult;
import com.okaka.onenightjinroh.application.domain.GameResult;
import com.okaka.onenightjinroh.application.domain.HolidayRoles;
import com.okaka.onenightjinroh.application.domain.Judge;
import com.okaka.onenightjinroh.application.domain.KaitoNightActFormatter;
import com.okaka.onenightjinroh.application.logic.KaitoNightActExecuteLogic;
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
    @Autowired
    KaitoNightActExecuteLogic kaitoNightActExecuteLogic;

    public GameResult invoke(Long gameId, Long gameParticipantId) {
        KaitoNightActFormatter kaitoNightActFormatter = kaitoNightActRepository.findByGameId(gameId)
                .map(kaitoNightAct -> kaitoNightActExecuteLogic.invoke(kaitoNightAct))
                .orElse(null);

        WinLoseConditionBase condition = judgeFacade.judge(gameId, kaitoNightActFormatter);
        GameParticipants gameParticipants = gameParticipantRepository.findAllByGameIdWithUserAndRole(gameId);

        GameParticipantsConsideredNightAct gameParticipantsConsideredNightAct = new GameParticipantsConsideredNightAct(gameParticipants, kaitoNightActFormatter);
        GameParticipantsWithResult gameParticipantsWithResult = new GameParticipantsWithResult(condition, gameParticipantsConsideredNightAct);

        HolidayRoles holidayRoles = holidayRolesPort.findByGameId(gameId);
        return new GameResult(gameId, gameParticipantId, new Judge(condition.getResultText()), gameParticipantsWithResult,
                holidayRoles);
    }
}
