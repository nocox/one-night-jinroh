package com.okaka.onenightjinroh.application.domain;

public class Vote {
    public Long voteId;
    public Long gameParticipationId;
    public Long toGameParticipationId;
    public boolean peaceVillageFlg;

    public Vote(Long voteId) {
        this.voteId = voteId;
    }

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

    public void setGameParticipationId(Long gameParticipationId) {
        this.gameParticipationId = gameParticipationId;
    }

    public void setToGameParticipationId(Long toGameParticipationId) {
        this.toGameParticipationId = toGameParticipationId;
    }

    public void setPeaceVillageFlg(boolean peaceVillageFlg) {
        this.peaceVillageFlg = peaceVillageFlg;
    }
}
