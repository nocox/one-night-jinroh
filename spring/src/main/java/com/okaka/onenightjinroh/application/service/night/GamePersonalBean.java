package com.okaka.onenightjinroh.application.service.night;

public class GamePersonalBean {
    public Long gameId;
    public Long gameParticipationId;

    public GamePersonalBean(Long gameId, Long gameParticipationId) {
        this.gameId = gameId;
        this.gameParticipationId = gameParticipationId;
    }

    public Long getGameId() {
        return gameId;
    }

    public Long getGameParticipationId() {
        return gameParticipationId;
    }
}
