package com.okaka.onenightjinroh.application.service.result;

public class GameParticipantWithResultBean {
    private final String playerName;
    private final String role;
    private final String coRole;
    private final String winOrLose;
    private final boolean myself;
    private final String comment;

    public GameParticipantWithResultBean(String playerName, String role, String coRole, String winOrLose, boolean myself, String comment) {
        this.playerName = playerName;
        this.role = role;
        this.coRole = coRole;
        this.winOrLose = winOrLose;
        this.myself = myself;
        this.comment = comment;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getRole() {
        return role;
    }

    public String getCoRole() {
        return coRole;
    }

    public String getWinOrLose() {
        return winOrLose;
    }

    public boolean isMyself() {
        return myself;
    }

    public String getComment() {
        return comment;
    }
}
