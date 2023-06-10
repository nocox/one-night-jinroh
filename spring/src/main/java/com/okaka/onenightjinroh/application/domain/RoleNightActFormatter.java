package com.okaka.onenightjinroh.application.domain;

public interface RoleNightActFormatter {
    public String toActLog();
    public boolean showableParticipant(Long participantId);
}
