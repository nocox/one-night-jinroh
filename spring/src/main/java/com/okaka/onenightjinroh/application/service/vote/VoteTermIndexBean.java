package com.okaka.onenightjinroh.application.service.vote;

import com.okaka.onenightjinroh.application.bean.GameIndexBean;
import com.okaka.onenightjinroh.application.service.talk.CoStateBean;

import java.util.List;

public class VoteTermIndexBean {
    private Long gameId;
    private GameIndexBean gameIndex;
    private VoteIndexBean voteIndex;
    private final List<CoStateBean.CoBean> cos;

    public VoteTermIndexBean(Long gameId, GameIndexBean gameIndex, VoteIndexBean voteIndex, List<CoStateBean.CoBean> cos) {
        this.gameId = gameId;
        this.gameIndex = gameIndex;
        this.voteIndex = voteIndex;
        this.cos = cos;
    }

    public Long getGameId() {
        return gameId;
    }

    public GameIndexBean getGameIndex() {
        return gameIndex;
    }

    public VoteIndexBean getVoteIndex() {
        return voteIndex;
    }

    public List<CoStateBean.CoBean> getCos() {
        return cos;
    }
}
