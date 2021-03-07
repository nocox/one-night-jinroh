package com.okaka.onenightjinroh.application.service.tally;

import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.GameParticipantBean;

public class GameParticipantWithVoteBean extends GameParticipantBean {
    private Long voteCount;

    public GameParticipantWithVoteBean(GameParticipant gameParticipant, Long voteCount) {
        super(gameParticipant);
        this.voteCount = voteCount;
    }

    public Long getVoteCount() {
        return voteCount;
    }
}
