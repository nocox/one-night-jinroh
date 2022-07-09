package com.okaka.onenightjinroh.application.service.night;

import com.okaka.onenightjinroh.application.bean.GameIndexBean;

public class NightTermIndexBean {
    private Long gameId;
    private GameIndexBean gameIndex;
    private boolean doneNightAct;

    public NightTermIndexBean(Long gameId, GameIndexBean gameIndex, boolean doneNightAct) {
        this.gameId = gameId;
        this.gameIndex = gameIndex;
        this.doneNightAct = doneNightAct;
    }

    public Long getGameId() {
        return gameId;
    }

    public GameIndexBean getGameIndex() {
        return gameIndex;
    }

    public boolean isDoneNightAct() {
        return doneNightAct;
    }
}
