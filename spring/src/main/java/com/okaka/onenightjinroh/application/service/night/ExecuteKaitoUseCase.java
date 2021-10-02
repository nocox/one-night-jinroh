package com.okaka.onenightjinroh.application.service.night;

import com.okaka.onenightjinroh.api.bean.NightKaitoResultBean;
import com.okaka.onenightjinroh.application.domain.KaitoNightAct;
import com.okaka.onenightjinroh.application.domain.KaitoNightActFormatter;
import com.okaka.onenightjinroh.application.logic.KaitoNightActExecuteLogic;
import com.okaka.onenightjinroh.application.repository.KaitoNightActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExecuteKaitoUseCase {
    @Autowired
    KaitoNightActRepository kaitoNightActRepository;
    @Autowired
    KaitoNightActExecuteLogic executeLogic;

    public NightKaitoResultBean invoke(Long fromId, Long toId) {

        KaitoNightAct kaitoNightAct = KaitoNightAct.of(fromId, toId);
        kaitoNightActRepository.save(kaitoNightAct);

        KaitoNightActFormatter formatter = executeLogic.invoke(kaitoNightAct);
        return new NightKaitoResultBean(formatter);
    }
}
