package com.okaka.onenightjinroh.application.service.night;

import com.okaka.onenightjinroh.adapter.HolidayRolesAdapter;
import com.okaka.onenightjinroh.application.domain.HolidayRoles;
import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.domain.User;
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
    HolidayRolesAdapter holidayRolesAdapter;
    @Autowired
    UserRepository userRepository;

    public NightUranaiResultDto invoke(Long gameId, Long participantId, NightUranaiStatus status) {
        switch (status){
            case PLAYER:
                assert participantId != null;
                User user = userRepository.findByParticipantId(participantId);
                List<Role> roles = queryService.find(Collections.singletonList(participantId));
                return new NightUranaiResultDto(roles, status, participantId, user);
            case HOLIDAY_ROLES:
                HolidayRoles holidayRoles = holidayRolesAdapter.findByGameId(gameId);
                return new NightUranaiResultDto(holidayRoles.getRoles(), status, null, null);
            case NOT_CHOOSE:
                return new NightUranaiResultDto(Collections.emptyList(), status, null, null);
            default:
                throw new IllegalArgumentException();
        }
    }
}
