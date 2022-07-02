package com.okaka.onenightjinroh.application.service.night;

import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.domain.UranaishiNightAct;
import com.okaka.onenightjinroh.application.domain.User;
import com.okaka.onenightjinroh.application.repository.UranaishiNightActRepository;
import com.okaka.onenightjinroh.application.repository.UserRepository;
import com.okaka.onenightjinroh.application.service.night.dto.NightUranaiResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GetUranaishiNightResultUseCase {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ExecuteNightUranaiQueryService queryService;
    @Autowired
    UranaishiNightActRepository uranaishiNightActRepository;

    public Optional<NightUranaiResultDto> invoke(Long participationId) {
        Optional<UranaishiNightAct> optUranaishiNightAct = uranaishiNightActRepository.findByParticipationId(participationId);
        if (optUranaishiNightAct.isEmpty()){
            return Optional.empty();
        }

        UranaishiNightAct uranaishiNightAct = optUranaishiNightAct.get();
        User user = userRepository.findByParticipantId(uranaishiNightAct.getToGameParticipationId());
        List<Role> roles = queryService.find(Collections.singletonList(uranaishiNightAct.getToGameParticipationId()));

        return Optional.of(new NightUranaiResultDto(
                roles,
                NightUranaiStatus.PLAYER,
                uranaishiNightAct.getToGameParticipationId(),
                user
        ));
    }
}
