package com.okaka.onenightjinroh.application.domain;

import com.okaka.onenightjinroh.application.domain.RoleNightActFormatter;

// TODO: 時が来たら消します
public class OtherNightActFormatter implements RoleNightActFormatter {

    @Override
    public String toActLog() {
        return "この役職はまだ実装されてないよ";
    }
}
