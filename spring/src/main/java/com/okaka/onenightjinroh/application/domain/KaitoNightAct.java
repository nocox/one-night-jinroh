package com.okaka.onenightjinroh.application.domain;

public class KaitoNightAct {
    private final Long fromGameParticipationId;
    private final Long toGameParticipationId;

    private KaitoNightAct(final Long fromGameParticipationId, final Long toGameParticipationId) {
        this.fromGameParticipationId = fromGameParticipationId;
        this.toGameParticipationId = toGameParticipationId;
    }

    public static KaitoNightAct of(final Long fromGameParticipationId, final Long toGameParticipationId) {
        return new KaitoNightAct(fromGameParticipationId, toGameParticipationId);
    }

    public static KaitoNightAct noAct(final Long fromGameParticipationId) {
        return new KaitoNightAct(fromGameParticipationId, null);
    }

    public Long getFromGameParticipationId() {
        return fromGameParticipationId;
    }

    public Long getToGameParticipationId() {
        return toGameParticipationId;
    }
}
