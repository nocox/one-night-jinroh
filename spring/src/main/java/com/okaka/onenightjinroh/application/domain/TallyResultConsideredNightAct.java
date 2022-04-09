package com.okaka.onenightjinroh.application.domain;

import java.util.Optional;

public class TallyResultConsideredNightAct{
    private final TallyResult tallyResult;
    private final Optional<KaitoNightActFormatter> kaitoNightActFormatter;

    public TallyResultConsideredNightAct(TallyResult tallyResult, Optional<KaitoNightActFormatter> kaitoNightActFormatter) {
        this.tallyResult = tallyResult;
        this.kaitoNightActFormatter = kaitoNightActFormatter;
    }

    public Role getRole() {
        GameParticipant participant = tallyResult.getGameParticipant();
        return kaitoNightActFormatter.map(act -> act.considerRole(participant))
                .orElse(participant.getRole());
    }

    public boolean getSelected() {
        return tallyResult.getSelected();
    }

    public TallyResult getTallyResult() {
        return tallyResult;
    }
}
