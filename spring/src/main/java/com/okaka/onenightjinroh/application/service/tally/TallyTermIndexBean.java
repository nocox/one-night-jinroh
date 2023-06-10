package com.okaka.onenightjinroh.application.service.tally;

import com.okaka.onenightjinroh.application.bean.GameIndexBean;
import com.okaka.onenightjinroh.application.service.talk.CoStateBean;

import java.util.List;

public class TallyTermIndexBean {
    private Long gameId;
    public GameIndexBean gameIndex;
    public TallyResultBean tallyResult;
    private final List<CoStateBean.CoBean> cos;

    public Long getGameId() {
        return gameId;
    }

    public GameIndexBean getGameIndex() {
        return gameIndex;
    }

    public TallyResultBean getTallyResult() {
        return tallyResult;
    }

    public List<CoStateBean.CoBean> getCos() {
        return cos;
    }

    public TallyTermIndexBean(Long gameId, GameIndexBean gameIndex, TallyResultBean tallyResult, List<CoStateBean.CoBean> cos) {
        this.gameId = gameId;
        this.gameIndex = gameIndex;
        this.tallyResult = tallyResult;
        this.cos = cos;
    }
}
