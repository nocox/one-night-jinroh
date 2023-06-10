package com.okaka.onenightjinroh.application.domain;

// 空文字で「夜の行動を持たない」を表現している
public class OtherNightActFormatter implements RoleNightActFormatter {

    @Override
    public String toActLog() {
        return "";
    }

    @Override
    public boolean showableParticipant(Long participantId) {
        return false;
    }
}
