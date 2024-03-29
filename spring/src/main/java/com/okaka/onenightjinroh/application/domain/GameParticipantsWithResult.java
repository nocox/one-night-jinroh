package com.okaka.onenightjinroh.application.domain;

import com.okaka.onenightjinroh.application.service.result.WinLoseConditionBase;

import java.util.stream.Stream;

public class GameParticipantsWithResult {
    private final GameParticipantsConsideredNightAct participants;
    private final WinLoseConditionBase winLoseCondition;

    public GameParticipantsWithResult(GameParticipantsConsideredNightAct participants, WinLoseConditionBase winLoseCondition) {
        this.participants = participants;
        this.winLoseCondition = winLoseCondition;
    }

    public Stream<GameParticipantWithResult> stream() {
        return participants.stream().map(GameParticipantWithResult::new);
    }

    public class GameParticipantWithResult{
        private final GameParticipantsConsideredNightAct.GameParticipantConsideredNightAct participant;

        public GameParticipantWithResult(GameParticipantsConsideredNightAct.GameParticipantConsideredNightAct participant) {
            this.participant = participant;
        }

        public String getPlayerName() {
            return participant.getParticipant().getUser().getUserName();
        }

        public String getOriginalRoleEngStr() {
            return participant.getParticipant().getRole().getRoleEngName();
        }

        public String getRoleEngStrConsideredNightAct() {
            return participant.getRoleConsideredNightAct().getRoleEngName();
        }

        public String getCoRoleEngStr() {
            return "mada dayo";
        }

        public String getWinOrLose() {
            Role roleConsideredNightAct = participant.getRoleConsideredNightAct();
            return winLoseCondition.judge(roleConsideredNightAct).name();
        }

        public boolean myself(Long participantId) {
            return participant.getParticipant().getGameParticipationId().equals(participantId);
        }

        public String getComment() {
            return "mada dayo";
        }
    }
}
