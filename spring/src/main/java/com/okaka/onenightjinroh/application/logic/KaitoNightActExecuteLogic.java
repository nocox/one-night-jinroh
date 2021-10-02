package com.okaka.onenightjinroh.application.logic;

import com.okaka.onenightjinroh.application.domain.KaitoNightAct;
import com.okaka.onenightjinroh.application.domain.KaitoNightActFormatter;
import org.springframework.stereotype.Component;

@Component
public class KaitoNightActExecuteLogic {
    public KaitoNightActFormatter invoke(KaitoNightAct kaitoNightAct) {

        return new KaitoNightActFormatter();
    }
}
