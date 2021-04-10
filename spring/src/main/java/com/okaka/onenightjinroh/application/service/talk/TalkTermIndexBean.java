package com.okaka.onenightjinroh.application.service.talk;

import com.okaka.onenightjinroh.application.domain.GameIndexBean;

public class TalkTermIndexBean {
    private Long gameId;
    private GameIndexBean gameIndex;

    public TalkTermIndexBean(GameIndexBean gameIndex, Long gameId) {
        this.gameIndex = gameIndex;
        this.gameId = gameId;
    }

    public GameIndexBean getGameIndex() {
        return gameIndex;
    }

    public Long getGameId() {
        return gameId;
    }
}
