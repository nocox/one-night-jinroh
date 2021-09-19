package com.okaka.onenightjinroh.application.service.talk;

import com.okaka.onenightjinroh.application.bean.GameIndexBean;

public class TalkTermIndexBean {
    private final Long gameId;
    private final GameIndexBean gameIndex;
    private final String nightActText;

    public TalkTermIndexBean(GameIndexBean gameIndex, Long gameId, String nightActText) {
        this.gameIndex = gameIndex;
        this.gameId = gameId;
        this.nightActText = nightActText;
    }

    public GameIndexBean getGameIndex() {
        return gameIndex;
    }

    public Long getGameId() {
        return gameId;
    }

    public String getNightActText() {
        return nightActText;
    }
}
