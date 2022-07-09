package com.okaka.onenightjinroh.application.service.night;

import com.okaka.onenightjinroh.application.domain.KaitoNightActFormatter;
import com.okaka.onenightjinroh.application.repository.KaitoNightActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetKaitoNightResultUseCase {
    @Autowired
    KaitoNightActRepository repository;

    public Optional<KaitoNightActFormatter> invoke(Long gameParticipantId) {
        return repository.findByParticipationId(gameParticipantId);
    }

}
