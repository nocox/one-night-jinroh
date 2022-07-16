package com.okaka.onenightjinroh.application.service.night;

import com.okaka.onenightjinroh.adapter.HolidayRolesAdapter;
import com.okaka.onenightjinroh.application.domain.HolidayRoles;
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
    HolidayRolesAdapter holidayRolesAdapter;
    @Autowired
    UranaishiNightActRepository uranaishiNightActRepository;

    public Optional<NightUranaiResultDto> invoke(Long gameId, Long participationId) {
        Optional<UranaishiNightAct> optUranaishiNightAct = uranaishiNightActRepository.findByParticipationId(participationId);
        if (optUranaishiNightAct.isEmpty()){
            return Optional.empty();
        }

        UranaishiNightAct uranaishiNightAct = optUranaishiNightAct.get();
        return getUranaiResult(uranaishiNightAct, gameId);
    }

    private Optional<NightUranaiResultDto> getUranaiResult(UranaishiNightAct uranaishiNightAct, Long gameId) {
        if (uranaishiNightAct.isSelectedHolidayRoles()) {
            HolidayRoles holidayRoles = holidayRolesAdapter.findByGameId(gameId);
            return Optional.of(new NightUranaiResultDto(
                    holidayRoles.getRoles(),
                    NightUranaiStatus.HOLIDAY_ROLES,
                    uranaishiNightAct.getToGameParticipationId(),
                    null
            ));
        }
        List<Role> roles = queryService.find(Collections.singletonList(uranaishiNightAct.getToGameParticipationId()));
        User user = userRepository.findByParticipantId(uranaishiNightAct.getToGameParticipationId());
        return Optional.of(new NightUranaiResultDto(
                roles,
                NightUranaiStatus.PLAYER,
                uranaishiNightAct.getToGameParticipationId(),
                user
        ));
    }
}
