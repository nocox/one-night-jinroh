package com.okaka.onenightjinroh.application.service.vote;

import com.okaka.onenightjinroh.application.bean.GameIndexBean;

public class VoteTermIndexBean {
    private Long gameId;
    private GameIndexBean gameIndex;
    private VoteIndexBean voteIndex;

    public VoteTermIndexBean(Long gameId, GameIndexBean gameIndex, VoteIndexBean voteIndex) {
        this.gameId = gameId;
        this.gameIndex = gameIndex;
        this.voteIndex = voteIndex;
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
}
