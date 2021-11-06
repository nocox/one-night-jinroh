package com.okaka.onenightjinroh.application.service.talk;

import com.okaka.onenightjinroh.application.bean.GameIndexBean;
import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.repository.GameParticipantRepository;
import com.okaka.onenightjinroh.application.repository.RoleNightActFormatterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTalkTermIndexUseCase {

    @Autowired
    GameParticipantRepository gameParticipantRepository;
    @Autowired
    RoleNightActFormatterRepository roleNightActFormatterRepository;

    public TalkTermIndexBean get(Long gameId, Long gameParticipantId) {
        List<GameParticipant> gameParticipants = gameParticipantRepository.findByGameIdWithUserAndRole(gameId);
        String nightActLog = roleNightActFormatterRepository.fetchNightAct(gameId, gameParticipantId).toActLog();

        GameIndexBean gameIndex = GameIndexBean.withRoleActLog(gameParticipants, gameParticipantId, nightActLog);
        return new TalkTermIndexBean(gameIndex, gameId);
    }
}
