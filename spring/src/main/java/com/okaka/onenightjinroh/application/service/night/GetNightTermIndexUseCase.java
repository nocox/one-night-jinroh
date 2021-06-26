package com.okaka.onenightjinroh.application.service.night;

import com.okaka.onenightjinroh.application.bean.GameIndexBean;
import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.repository.GameParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetNightTermIndexUseCase {

    @Autowired
    GameParticipantRepository gameParticipantRepository;

    public NightTermIndexBean get(Long gameId, Long gameParticipantId) {
        List<GameParticipant> gameParticipants = gameParticipantRepository.findByGameIdWithUserAndRole(gameId);
        GameIndexBean gameIndexBean = new GameIndexBean(gameParticipants, gameParticipantId);
        return new NightTermIndexBean(gameId, gameIndexBean);
    }
}
