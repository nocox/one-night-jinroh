package com.okaka.onenightjinroh.application.service.vote;

import com.okaka.onenightjinroh.application.bean.GameParticipantBean;

import java.util.List;

public class VoteIndexBean {
    private List<GameParticipantBean> canVotePlayers;
    private Long votingDestination;

    public VoteIndexBean(List<GameParticipantBean> canVotePlayers, Long votingDestination) {
        this.canVotePlayers = canVotePlayers;
        this.votingDestination = votingDestination;
    }

    public List<GameParticipantBean> getCanVotePlayers() {
        return canVotePlayers;
    }

    public Long getVotingDestination() {
        return votingDestination;
    }
}
