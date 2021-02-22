package com.okaka.onenightjinroh.application.service.vote;

import com.okaka.onenightjinroh.application.domain.GameParticipantBean;

import java.util.List;

public class VoteIndexBean {
    private List<GameParticipantBean> canVotePlayers;

    public VoteIndexBean(List<GameParticipantBean> canVotePlayers) {
        this.canVotePlayers = canVotePlayers;
    }

    public List<GameParticipantBean> getCanVotePlayers() {
        return canVotePlayers;
    }
}
