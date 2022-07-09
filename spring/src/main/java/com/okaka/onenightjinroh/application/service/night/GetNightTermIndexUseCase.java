package com.okaka.onenightjinroh.application.service.night;

import com.okaka.onenightjinroh.application.bean.GameIndexBean;
import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.NightAct;
import com.okaka.onenightjinroh.application.repository.GameParticipantRepository;
import com.okaka.onenightjinroh.application.repository.NightActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetNightTermIndexUseCase {

    @Autowired
    GameParticipantRepository gameParticipantRepository;

    @Autowired
    NightActRepository nightActRepository;

    public NightTermIndexBean get(Long gameId, Long gameParticipantId) {
        List<GameParticipant> gameParticipants = gameParticipantRepository.findByGameIdWithUserAndRole(gameId);
        Optional<NightAct> optNightAct = nightActRepository.findByGameParticipantId(gameParticipantId);
        GameIndexBean gameIndexBean = GameIndexBean.ofHideRole(gameParticipants, gameParticipantId);
        return new NightTermIndexBean(gameId, gameIndexBean, optNightAct.isPresent());
    }
}
