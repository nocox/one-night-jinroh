package com.okaka.onenightjinroh.application.repository;


import com.okaka.onenightjinroh.adapter.HolidayRolesAdapter;
import com.okaka.onenightjinroh.application.domain.OtherNightActFormatter;
import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.domain.RoleNightActFormatter;
import com.okaka.onenightjinroh.application.domain.UranaishiNightActFormatter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleNightActFormatterRepository {

    @Autowired
    UranaishiNightActRepository uranaishiNightActRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    HolidayRolesAdapter holidayRolesAdapter;
    @Autowired
    GameParticipantRepository gameParticipantRepository;
    @Autowired
    KaitoNightActRepository kaitoNightActRepository;

    public Optional<? extends RoleNightActFormatter> fetchNightAct(Long gameId, Long gameParticipantId) {
        Role role = roleRepository.findByParticipationId(gameParticipantId);
        if (role instanceof Role.Uranaishi){
            final var uranaishiNightAct = uranaishiNightActRepository.findByParticipationId(gameParticipantId).orElse(null);
            if ( uranaishiNightAct == null ) {
                return Optional.empty();
            }
            final var toParticipant = gameParticipantRepository.findByParticipantId(uranaishiNightAct.getToGameParticipationId());
            final var holidayRoles = holidayRolesAdapter.findByGameId(gameId).getRoles();
            return Optional.of(UranaishiNightActFormatter.from(toParticipant, uranaishiNightAct, holidayRoles));
        }
        else if (role instanceof Role.Kaito) {
            return kaitoNightActRepository.findByParticipationId(gameParticipantId);
        }
        return Optional.of(new OtherNightActFormatter());
    }
}
