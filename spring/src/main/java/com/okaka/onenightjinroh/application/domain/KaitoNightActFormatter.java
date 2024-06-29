package com.okaka.onenightjinroh.application.domain;


public class KaitoNightActFormatter implements RoleNightActFormatter {
    private final GameParticipant toParticipant;
    private final GameParticipant fromParticipant;

    public KaitoNightActFormatter(GameParticipant toParticipant, GameParticipant fromParticipant) {
        this.toParticipant = toParticipant;
        this.fromParticipant = fromParticipant;
    }

    public static KaitoNightActFormatter of(GameParticipant toParticipant, GameParticipant fromParticipant) {
        return new KaitoNightActFormatter(toParticipant, fromParticipant);
    }

    public Role considerRole(GameParticipant participant) {
        Long myId = participant.getGameParticipationId();
        if (myId.equals(toParticipant.getGameParticipationId())) {
            return fromParticipant.getRole();
        } else if (myId.equals(fromParticipant.getGameParticipationId())) {
            return toParticipant.getRole();
        } else {
            return participant.getRole();
        }
    }

    @Override
    public String toActLog() {
        return String.format("怪盗の結果: %sと役職を交換しました．現在の役職はあなたが%sで，%sが%sです．",
                toParticipant.getUser().getUserName(),
                toParticipant.getRole().getRoleName(),
                toParticipant.getUser().getUserName(),
                fromParticipant.getRole().getRoleName());
    }

    @Override
    public boolean showableParticipant(Long participantId) {
        // 怪盗が何も選んでいない場合は、何も見えない。
        if (toParticipant == null) {
            return false;
        }
        return toParticipant.getGameParticipationId().equals(participantId);
    }

    public GameParticipant getToParticipant() {
        return toParticipant;
    }

    public GameParticipant getFromParticipant() {
        return fromParticipant;
    }


    public static KaitoNightActFormatter empty() {
        return new KaitoNightActFormatter.EmptyNightActFormatter(null, null);
    }

    public static class EmptyNightActFormatter extends KaitoNightActFormatter {
        public EmptyNightActFormatter(GameParticipant toParticipant, GameParticipant fromParticipant) {
            super(toParticipant, fromParticipant);
        }

        @Override
        public String toActLog() {
            return "怪盗の結果: " + "今回は誰とも役職交換をしませんでした";
        }
    }
}
