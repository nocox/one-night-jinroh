package com.okaka.onenightjinroh.application.service.talk;

import com.okaka.onenightjinroh.application.domain.GameIndexBean;

public class TalkTermIndexBean {
    private GameIndexBean gameIndex;

    public TalkTermIndexBean(GameIndexBean gameIndex) {
        this.gameIndex = gameIndex;
    }

    public GameIndexBean getGameIndex() {
        return gameIndex;
    }
}
