package com.okaka.onenightjinroh.application.service.talk;

import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.domain.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UranaishiNightActFormatter implements RoleNightActFormatter {
    private final Optional<GameParticipant> toParticipant;
    private final Collection<Role> resultRole;
    private final boolean selectedHolidayRoles;

    public UranaishiNightActFormatter(Optional<GameParticipant> toParticipant, Collection<Role> resultRole, boolean selectedHolidayRoles) {
        this.toParticipant = toParticipant;
        this.resultRole = resultRole;
        this.selectedHolidayRoles = selectedHolidayRoles;
    }

    @Override
    public String toActText() {
        String targetStr = toParticipant
                .map(GameParticipant::getUser)
                .map(User::getUserName)
                .or(() -> {
                    if (selectedHolidayRoles) {
                        return Optional.of("使われていない役職");
                    }
                    return Optional.empty();
                }).orElseThrow();

        String resultRoleStr = resultRole.stream()
                .map(Role::getRoleName)
                .reduce((sum, elm) -> sum + ", " + elm)
                .orElseThrow();

        return "占い結果: " + targetStr + "は，" + resultRoleStr + "です";
    }

    public static UranaishiNightActFormatter empty() {
        return new EmptyNightActFormatter(Optional.empty(), List.of(), false);
    }

    public static class EmptyNightActFormatter extends UranaishiNightActFormatter {
        public EmptyNightActFormatter(Optional<GameParticipant> toParticipant, Collection<Role> resultRole, boolean selectedHolidayRoles) {
            super(toParticipant, resultRole, selectedHolidayRoles);
        }

        @Override
        public String toActText() {
            return "占い結果: " + "今回は誰も占いませんでした";
        }
    }
}
