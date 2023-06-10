package com.okaka.onenightjinroh.application.domain;

import java.util.List;
import java.util.stream.Stream;

// TODO: Listで使用している箇所全てで反映する
public class GameParticipants {

    private final List<GameParticipant> participants;

    private GameParticipants(List<GameParticipant> participants) {
        this.participants = participants;
    }

    public static GameParticipants of(List<GameParticipant> participants) {
        return new GameParticipants(participants);
    }

    public GameParticipant mySelf(Long participantId) {
        return participants.stream()
                .filter(domain -> domain.getGameParticipationId().equals(participantId))
                .findFirst()
                .orElseThrow();
    }

    public Stream<GameParticipant> stream(){
        return participants.stream();
    }
}
