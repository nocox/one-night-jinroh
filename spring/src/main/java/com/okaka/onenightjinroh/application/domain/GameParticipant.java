package com.okaka.onenightjinroh.application.domain;

public class GameParticipant {
    public Long gameParticipationId;
    public Game game;
    public User user;
    public Role role;
    public boolean hostFlg;

    public GameParticipant(Long gameParticipationId, Game game, User user, Role role, boolean hostFlg) {
        this.gameParticipationId = gameParticipationId;
        this.game = game;
        this.user = user;
        this.role = role;
        this.hostFlg = hostFlg;
    }

    public GameParticipant(Long gameParticipationId) {
        this.gameParticipationId = gameParticipationId;
    }

    public void setGameParticipationId(Long gameParticipationId) {
        this.gameParticipationId = gameParticipationId;
    }

    public void setHostFlg(boolean hostFlg) {
        this.hostFlg = hostFlg;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getGameParticipationId() {
        return gameParticipationId;
    }

    public Game getGame() {
        return game;
    }

    public User getUser() {
        return user;
    }

    public Role getRole() {
        return role;
    }

    public boolean isHostFlg() {
        return hostFlg;
    }
}
