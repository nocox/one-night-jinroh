package com.okaka.onenightjinroh.application.service.talk;

import com.okaka.onenightjinroh.application.domain.GameIndexBean;
import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.GameParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTalkTermIndexUseCase {

    @Autowired
    GameParticipantRepository gameParticipantRepository;

    public TalkTermIndexBean get(Long gameId, Long gameParticipantId) {
        List<GameParticipant> gameParticipants = gameParticipantRepository.findByGameIdWithUserAndRole(gameId);
        GameIndexBean gameIndex = GameIndexBean.createHideRole(gameParticipants, gameParticipantId);
        return new TalkTermIndexBean(gameIndex, gameId);
    }
}
