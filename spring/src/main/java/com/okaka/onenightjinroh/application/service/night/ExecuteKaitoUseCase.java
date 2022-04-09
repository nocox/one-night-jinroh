package com.okaka.onenightjinroh.application.service.night;

import com.okaka.onenightjinroh.api.bean.NightKaitoResultBean;
import com.okaka.onenightjinroh.application.domain.KaitoNightActFormatter;
import com.okaka.onenightjinroh.application.repository.KaitoNightActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExecuteKaitoUseCase {
    @Autowired
    KaitoNightActRepository kaitoNightActRepository;

    public NightKaitoResultBean invoke(Long fromId, Long toId) {

        kaitoNightActRepository.save(fromId, toId);

        KaitoNightActFormatter formatter = kaitoNightActRepository.toDomain(fromId, toId);
        return new NightKaitoResultBean(formatter);
    }
}
