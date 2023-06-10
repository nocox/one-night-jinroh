package com.okaka.onenightjinroh.application.service.talk;

import com.okaka.onenightjinroh.application.bean.GameIndexBean;

import java.util.List;

public class TalkTermIndexBean {
    private final Long gameId;
    private final GameIndexBean gameIndex;
    private final List<CoStateBean.CoBean> cos;

    public TalkTermIndexBean(Long gameId, GameIndexBean gameIndex, List<CoStateBean.CoBean> coStateList) {
        this.gameId = gameId;
        this.gameIndex = gameIndex;
        this.cos = coStateList;
    }

    public GameIndexBean getGameIndex() {
        return gameIndex;
    }

    public Long getGameId() {
        return gameId;
    }

    public List<CoStateBean.CoBean> getCos() {
        return cos;
    }
}
