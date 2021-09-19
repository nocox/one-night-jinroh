package com.okaka.onenightjinroh.application.service.talk;

import com.okaka.onenightjinroh.adapter.HolidayRolesAdapter;
import com.okaka.onenightjinroh.application.bean.GameIndexBean;
import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.repository.GameParticipantRepository;
import com.okaka.onenightjinroh.application.repository.RoleRepository;
import com.okaka.onenightjinroh.application.repository.UranaishiNightActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetTalkTermIndexUseCase {

    @Autowired
    GameParticipantRepository gameParticipantRepository;
    @Autowired
    UranaishiNightActRepository uranaishiNightActRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    HolidayRolesAdapter holidayRolesAdapter;

    public TalkTermIndexBean get(Long gameId, Long gameParticipantId) {
        List<GameParticipant> gameParticipants = gameParticipantRepository.findByGameIdWithUserAndRole(gameId);
        GameIndexBean gameIndex = new GameIndexBean(gameParticipants, gameParticipantId);

        Role role = roleRepository.findByParticipationId(gameParticipantId);
        String nightActText = fetchNightAct(role, gameId, gameParticipantId).toActText();
        return new TalkTermIndexBean(gameIndex, gameId, nightActText);
    }

    private RoleNightActFormatter fetchNightAct(Role role, Long gameId, Long gameParticipantId) {
        if (role instanceof Role.Uranaishi){
            final var uranaishiNightAct = uranaishiNightActRepository.findByFromParticipationId(gameParticipantId).orElseThrow();
            final var toParticipant = gameParticipantRepository.findByParticipantId(uranaishiNightAct.getToGameParticipationId());
            final var roles = toParticipant
                    .map(GameParticipant::getRole).map(List::of)
                    .or(() -> {
                        if (uranaishiNightAct.isSelectedHolidayRoles()) {
                            return Optional.of(holidayRolesAdapter.findByGameId(gameId).getRoles());
                        }
                        return Optional.empty();
                    }).orElse(List.of());
            return toParticipant
                    .map(it -> new UranaishiNightActFormatter(toParticipant, roles, false))
                    .or(() -> {
                        if (uranaishiNightAct.isSelectedHolidayRoles()) {
                            return Optional.of(new UranaishiNightActFormatter(Optional.empty(), roles, true));
                        }
                        return Optional.empty();
                    })
                    .orElse(UranaishiNightActFormatter.empty());
        }
        return new OtherNightActFormatter();
    }
}
