package com.okaka.onenightjinroh.api.bean;

import com.okaka.onenightjinroh.application.domain.KaitoNightActFormatter;

public class NightKaitoResultBean {
    private final String actLog;

    public NightKaitoResultBean(KaitoNightActFormatter formatter) {
        this.actLog = formatter.toActLog();
    }

    public String getActLog() {
        return actLog;
    }
}
