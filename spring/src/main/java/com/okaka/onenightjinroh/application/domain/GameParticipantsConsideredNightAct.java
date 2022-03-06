package com.okaka.onenightjinroh.application.domain;

import java.util.stream.Stream;

public class GameParticipantsConsideredNightAct {
    private final GameParticipants participants;
    private final KaitoNightActFormatter kaitoNightActFormatter;

    public GameParticipantsConsideredNightAct(GameParticipants participants, KaitoNightActFormatter kaitoNightActFormatter) {
        this.participants = participants;
        this.kaitoNightActFormatter = kaitoNightActFormatter;
    }

    public Stream<GameParticipantConsideredNightAct> stream() {
        return participants.stream().map(GameParticipantConsideredNightAct::new);
    }

    public class GameParticipantConsideredNightAct {
        private final GameParticipant gameParticipant;

        public GameParticipantConsideredNightAct(GameParticipant gameParticipant) {
            this.gameParticipant = gameParticipant;
        }

        public GameParticipant getParticipant() {
            return this.gameParticipant;
        }

        public Role getRoleConsideredNightAct() {
            if (kaitoNightActFormatter == null) {
                return this.gameParticipant.getRole();
            }

            Long toParticipationId = kaitoNightActFormatter.getToParticipant().getGameParticipationId();
            Long fromParticipationId = kaitoNightActFormatter.getFromParticipant().getGameParticipationId();
            Long participationId = gameParticipant.getGameParticipationId();

            if (toParticipationId.equals(participationId)) {
                return kaitoNightActFormatter.getFromParticipant().getRole();
            } else if (fromParticipationId.equals(participationId)) {
                return kaitoNightActFormatter.getToParticipant().getRole();
            }

            return this.gameParticipant.getRole();
        }
    }
}
