package com.okaka.onenightjinroh.application.domain;

public class TallyResultConsideredNightAct{
    private TallyResult tallyResult;
    private KaitoNightActFormatter kaitoNightActFormatter;

    public TallyResultConsideredNightAct(TallyResult tallyResult, KaitoNightActFormatter kaitoNightActFormatter) {
        this.tallyResult = tallyResult;
        this.kaitoNightActFormatter = kaitoNightActFormatter;
    }

    public Role getRole() {
        if (kaitoNightActFormatter == null){
            return tallyResult.getGameParticipant().getRole();
        }

        Long toId = kaitoNightActFormatter.getToParticipant().getGameParticipationId();
        Long fromId = kaitoNightActFormatter.getFromParticipant().getGameParticipationId();
        Long myId = tallyResult.getGameParticipant().getGameParticipationId();

        if( myId.equals(toId) ) {
            return new Role.Kaito(4L, "怪盗");
        } else if(myId.equals(fromId)) {
            return kaitoNightActFormatter.getToParticipant().getRole();
        } else {
            return tallyResult.getGameParticipant().getRole();
        }
    }

    public boolean getSelected() {
        return tallyResult.getSelected();
    }

    public TallyResult getTallyResult() {
        return tallyResult;
    }
}
