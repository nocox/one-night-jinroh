package com.okaka.onenightjinroh.application.domain;

import java.util.Optional;
import java.util.stream.Stream;

public class GameParticipantsConsideredNightAct {
    private final GameParticipants participants;
    private final Optional<KaitoNightActFormatter> kaitoNightActFormatter;

    public GameParticipantsConsideredNightAct(GameParticipants participants, Optional<KaitoNightActFormatter> kaitoNightActFormatter) {
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
            return kaitoNightActFormatter
                    .map(act -> act.considerRole(gameParticipant))
                    .orElse(gameParticipant.getRole());
        }
    }
}
