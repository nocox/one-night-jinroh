package com.okaka.onenightjinroh.application.service.night;

import com.okaka.onenightjinroh.domaimpl.HolidayRolesRepositoryImpl;
import com.okaka.onenightjinroh.application.domain.HolidayRoles;
import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.domain.UranaishiNightAct;
import com.okaka.onenightjinroh.application.domain.User;
import com.okaka.onenightjinroh.application.repository.UranaishiNightActRepository;
import com.okaka.onenightjinroh.application.repository.UserRepository;
import com.okaka.onenightjinroh.application.service.night.dto.NightUranaiResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ExecuteNightUranaiUseCase {
    @Autowired
    ExecuteNightUranaiQueryService queryService;
    @Autowired
    HolidayRolesRepositoryImpl holidayRolesRepositoryImpl;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UranaishiNightActRepository uranaishiNightActRepository;

    public NightUranaiResultDto invoke(Long gameId, Long fromParticipantId ,Long toParticipantId, NightUranaiStatus status) {
        switch (status) {
            case PLAYER:
                assert toParticipantId != null;
                User user = userRepository.findByParticipantId(toParticipantId);
                List<Role> roles = queryService.find(Collections.singletonList(toParticipantId));
                uranaishiNightActRepository.save(UranaishiNightAct.bySelectedPlayer(fromParticipantId, toParticipantId));
                return new NightUranaiResultDto(roles, status, toParticipantId, user);
            case HOLIDAY_ROLES:
                HolidayRoles holidayRoles = holidayRolesRepositoryImpl.findByGameId(gameId);
                uranaishiNightActRepository.save(UranaishiNightAct.bySelectedHolidayRoles(fromParticipantId));
                return new NightUranaiResultDto(holidayRoles.getRoles(), status, null, null);
            case NOT_CHOOSE:
                uranaishiNightActRepository.save(UranaishiNightAct.byNotSelected(fromParticipantId));
                return new NightUranaiResultDto(Collections.emptyList(), status, null, null);
            default:
                throw new IllegalArgumentException();
        }
    }
}
