package com.okaka.onenightjinroh.application.service.vote;

import com.okaka.onenightjinroh.application.domain.GameIndexBean;

public class VoteTermIndexBean {
    private Long gameId;
    private GameIndexBean gameIndex;

    public VoteTermIndexBean(Long gameId, GameIndexBean gameIndex) {
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
