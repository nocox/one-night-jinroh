package com.okaka.onenightjinroh.application.service.tally;

import com.okaka.onenightjinroh.application.domain.GameIndexBean;

public class TallyTermIndexBean {
    public GameIndexBean gameIndex;
    public TallyResultBean tallyResult;

    public GameIndexBean getGameIndex() {
        return gameIndex;
    }

    public TallyResultBean getTallyResult() {
        return tallyResult;
    }

    public TallyTermIndexBean(GameIndexBean gameIndex, TallyResultBean tallyResult) {
        this.gameIndex = gameIndex;
        this.tallyResult = tallyResult;
    }
}
