package com.okaka.onenightjinroh.application.domain;

public class NightAct {
    public Long nightActId;
    public GameParticipant gameParticipant;

    public void setNightActId(Long nightActId) {
        this.nightActId = nightActId;
    }

    public void setGameParticipant(GameParticipant gameParticipant) {
        this.gameParticipant = gameParticipant;
    }

    public Long getNightActId() {
        return nightActId;
    }

    public GameParticipant getGameParticipant() {
        return gameParticipant;
    }
}
