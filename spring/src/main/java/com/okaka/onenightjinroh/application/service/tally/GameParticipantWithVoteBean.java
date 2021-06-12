package com.okaka.onenightjinroh.application.service.tally;

import com.okaka.onenightjinroh.application.bean.GameParticipantBean;
import com.okaka.onenightjinroh.application.domain.GameParticipant;

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
