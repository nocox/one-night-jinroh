package com.okaka.onenightjinroh.application.domain;

public class Vote {
    public Long voteId;
    public Long gameParticipationId;
    public Long toGameParticipationId;
    public boolean peaceVillageFlg;

    public Vote(Long gameParticipationId, Long toGameParticipationId) {
        this.gameParticipationId = gameParticipationId;
        this.toGameParticipationId = toGameParticipationId;
    }

    public Long getVoteId() {
        return voteId;
    }

    public Long getGameParticipationId() {
        return gameParticipationId;
    }

    public Long getToGameParticipationId() {
        return toGameParticipationId;
    }

    public boolean isPeaceVillageFlg() {
        return peaceVillageFlg;
    }
}
