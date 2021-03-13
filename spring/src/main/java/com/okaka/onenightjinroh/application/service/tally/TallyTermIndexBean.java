package com.okaka.onenightjinroh.application.service.tally;

import com.okaka.onenightjinroh.application.domain.GameIndexBean;

public class TallyTermIndexBean {
    private Long gameId;
    public GameIndexBean gameIndex;
    public TallyResultBean tallyResult;

    public Long getGameId() {
        return gameId;
    }

    public GameIndexBean getGameIndex() {
        return gameIndex;
    }

    public TallyResultBean getTallyResult() {
        return tallyResult;
    }

    public TallyTermIndexBean(Long gameId, GameIndexBean gameIndex, TallyResultBean tallyResult) {
        this.gameId = gameId;
        this.gameIndex = gameIndex;
        this.tallyResult = tallyResult;
    }
}
