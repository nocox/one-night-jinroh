package com.okaka.onenightjinroh.application.service.result;

import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.GameResult;
import com.okaka.onenightjinroh.application.domain.HolidayRoles;
import com.okaka.onenightjinroh.application.domain.Judge;
import com.okaka.onenightjinroh.application.port.HolidayRolesPort;
import com.okaka.onenightjinroh.application.repository.GameParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetGameResultUseCase {

    @Autowired
    JudgeFacade judgeFacade;
    @Autowired
    GameParticipantRepository gameParticipantRepository;
    @Autowired
    HolidayRolesPort holidayRolesPort;

    public GameResult invoke(Long gameId, Long gameParticipantId) {
        Judge judge = judgeFacade.judge(gameId);
        List<GameParticipant> gameParticipants = gameParticipantRepository.findByGameIdWithUserAndRole(gameId);
        HolidayRoles holidayRoles = holidayRolesPort.findByGameId(gameId);
        return new GameResult(gameId, gameParticipantId, judge, gameParticipants, holidayRoles);
    }
}
