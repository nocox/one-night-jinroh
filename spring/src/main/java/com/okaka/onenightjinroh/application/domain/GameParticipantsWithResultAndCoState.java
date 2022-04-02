package com.okaka.onenightjinroh.application.domain;

import com.okaka.onenightjinroh.application.service.result.WinLoseConditionBase;

import java.util.Optional;
import java.util.stream.Stream;

public class GameParticipantsWithResultAndCoState {
    private final GameParticipantsConsideredNightAct participants;
    private final WinLoseConditionBase winLoseCondition;
    private final CoState coState;

    public GameParticipantsWithResultAndCoState(GameParticipantsConsideredNightAct participants, WinLoseConditionBase winLoseCondition, CoState coState) {
        this.participants = participants;
        this.winLoseCondition = winLoseCondition;
        this.coState = coState;
    }

    public Stream<GameParticipantWithResultAndCoState> stream() {
        return participants.stream().map(GameParticipantWithResultAndCoState::new);
    }

    public class GameParticipantWithResultAndCoState{
        private final GameParticipantsConsideredNightAct.GameParticipantConsideredNightAct participant;

        public GameParticipantWithResultAndCoState(GameParticipantsConsideredNightAct.GameParticipantConsideredNightAct participant) {
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
            Optional<CoState.Co> co = coState.getCos().stream()
            .filter(c-> c.getId().equals(participant.getParticipant().getGameParticipationId()))
            .findFirst();
            
            return co.map(c-> c.getRole().getRoleEngName()).orElse("");
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
