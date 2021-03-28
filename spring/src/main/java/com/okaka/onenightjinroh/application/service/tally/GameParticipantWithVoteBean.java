package com.okaka.onenightjinroh.application.service.tally;

import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.GameParticipantBean;

public class GameParticipantWithVoteBean extends GameParticipantBean {
    private Integer voteCount;

    public GameParticipantWithVoteBean(GameParticipant gameParticipant, Integer voteCount) {
        super(gameParticipant);
        this.voteCount = voteCount;
    }

    public Integer getVoteCount() {
        return voteCount;
    }
}
