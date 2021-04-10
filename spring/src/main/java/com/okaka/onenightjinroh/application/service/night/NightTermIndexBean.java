package com.okaka.onenightjinroh.application.service.night;

import com.okaka.onenightjinroh.application.domain.GameIndexBean;

public class NightTermIndexBean {
    private Long gameId;
    private GameIndexBean gameIndex;

    public NightTermIndexBean(Long gameId, GameIndexBean gameIndex) {
        this.gameId = gameId;
        this.gameIndex = gameIndex;
    }

    public Long getGameId() {
        return gameId;
    }

    public GameIndexBean getGameIndex() {
        return gameIndex;
    }
}
