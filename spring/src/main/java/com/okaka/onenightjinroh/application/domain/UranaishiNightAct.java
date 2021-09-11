package com.okaka.onenightjinroh.application.domain;


public class UranaishiNightAct {
    private final Long uranaishiNightActId;
    private final Long fromGameParticipationId;
    private final Long toGameParticipationId;
    private final boolean selectedHolidayRoles;

    public UranaishiNightAct(Long uranaishiNightActId, Long fromGameParticipationId, Long toGameParticipationId, boolean selectedHolidayRoles) {
        this.uranaishiNightActId = uranaishiNightActId;
        this.fromGameParticipationId = fromGameParticipationId;
        this.toGameParticipationId = toGameParticipationId;
        this.selectedHolidayRoles = selectedHolidayRoles;
    }

    public static UranaishiNightAct bySelectedPlayer(Long fromGameParticipationId, Long toGameParticipationId) {
        return new UranaishiNightAct(null, fromGameParticipationId, toGameParticipationId, false);
    }

    public static UranaishiNightAct bySelectedHolidayRoles(Long fromGameParticipationId) {
        return new UranaishiNightAct(null, fromGameParticipationId, null, true);
    }

    public static UranaishiNightAct byNotSelected(Long fromGameParticipationId) {
        return new UranaishiNightAct(null, fromGameParticipationId, null, false);
    }

    public Long getUranaishiNightActId() {
        return uranaishiNightActId;
    }

    public Long getFromGameParticipationId() {
        return fromGameParticipationId;
    }

    public Long getToGameParticipationId() {
        return toGameParticipationId;
    }

    public boolean isSelectedHolidayRoles() {
        return selectedHolidayRoles;
    }
}
