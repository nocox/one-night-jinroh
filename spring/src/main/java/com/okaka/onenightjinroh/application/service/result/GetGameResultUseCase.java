package com.okaka.onenightjinroh.application.service.result;

import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.GameResult;
import com.okaka.onenightjinroh.application.domain.Judge;
import com.okaka.onenightjinroh.application.domain.Role;
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

    public GameResult invoke(Long gameId, Long gameParticipantId) {
        Judge judge = judgeFacade.judge(gameId);
        List<GameParticipant> gameParticipants = gameParticipantRepository.findByGameIdWithUserAndRole(gameId);
        List<Role> holidayCard = null;
        return new GameResult(gameId, gameParticipantId, judge, gameParticipants, holidayCard);
    }
}
