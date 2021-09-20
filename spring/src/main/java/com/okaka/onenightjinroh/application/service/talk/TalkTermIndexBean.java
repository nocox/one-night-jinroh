package com.okaka.onenightjinroh.application.service.talk;

import com.okaka.onenightjinroh.application.bean.GameIndexBean;

public class TalkTermIndexBean {
    private final Long gameId;
    private final GameIndexBean gameIndex;

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
