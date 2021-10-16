package com.okaka.onenightjinroh.application.logic;

import com.okaka.onenightjinroh.application.domain.KaitoNightAct;
import com.okaka.onenightjinroh.application.domain.KaitoNightActFormatter;
import com.okaka.onenightjinroh.application.repository.GameParticipantRepository;
import com.okaka.onenightjinroh.application.repository.KaitoNightActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KaitoNightActExecuteLogic {
    @Autowired
    KaitoNightActRepository kaitoNightActRepository;
    @Autowired
    GameParticipantRepository gameParticipantRepository;

    // kaitouNightActが直前に取得できているときはこちらを使用する．
    public KaitoNightActFormatter invoke(KaitoNightAct kaitoNightAct) {
        Long toParticipationId = kaitoNightAct.getToGameParticipationId();
        Long fromParticipationId = kaitoNightAct.getFromGameParticipationId();
        final var toParticipant = gameParticipantRepository.findByParticipantId(toParticipationId);
        final var fromParticipant = gameParticipantRepository.findByParticipantId(fromParticipationId);

        return KaitoNightActFormatter.of(toParticipant, fromParticipant);
    }

    public KaitoNightActFormatter invokeByParticipationId(Long participationId) {
        KaitoNightAct kaitoNightAct = kaitoNightActRepository.findByParticipationId(participationId).orElseThrow();
        Long toParticipationId = kaitoNightAct.getToGameParticipationId();
        Long fromParticipationId = kaitoNightAct.getFromGameParticipationId();
        final var toParticipant = gameParticipantRepository.findByParticipantId(toParticipationId);
        final var fromParticipant = gameParticipantRepository.findByParticipantId(fromParticipationId);

        return KaitoNightActFormatter.of(toParticipant, fromParticipant);
    }

}
