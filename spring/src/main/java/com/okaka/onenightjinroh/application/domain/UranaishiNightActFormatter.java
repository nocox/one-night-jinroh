package com.okaka.onenightjinroh.application.domain;

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

    public static UranaishiNightActFormatter from(Optional<GameParticipant> toParticipant, UranaishiNightAct uranaishiNightAct, List<Role> holidayRoles) {
        final var roles = toParticipant
                .map(GameParticipant::getRole).map(List::of)
                .or(() -> {
                    if (uranaishiNightAct.isSelectedHolidayRoles()) {
                        return Optional.of(holidayRoles);
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

    @Override
    public boolean showableParticipant(Long gameParticipantId) {
        if (toParticipant.isEmpty()) {
            return false;
        }
        return toParticipant.get().getGameParticipationId().equals(gameParticipantId);
    }

    @Override
    public String toActLog() {
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
        public String toActLog() {
            return "占い結果: " + "今回は誰も占いませんでした";
        }
    }
}
