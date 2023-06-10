package com.okaka.onenightjinroh.api.bean;

import com.okaka.onenightjinroh.application.domain.KaitoNightActFormatter;

public class NightKaitoResultBean {
    private final Long selectedParticipantId;
    private final String actLog;

    public NightKaitoResultBean(KaitoNightActFormatter formatter) {
        this.selectedParticipantId = formatter.getToParticipant().getGameParticipationId();
        this.actLog = formatter.toActLog();
    }

    public String getActLog() {
        return actLog;
    }

    public Long getSelectedParticipantId() {
        return selectedParticipantId;
    }
}
