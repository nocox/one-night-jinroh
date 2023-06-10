package com.okaka.onenightjinroh.application.domain;

public class Game {
    public Long gameId;
    public Room room;
    public Rule rule;

    public Game(Long gameId) {
        this.gameId = gameId;
        this.room = null;
        this.rule = null;
    }

    public Game(Long gameId, Room room, Rule rule) {
        this.gameId = gameId;
        this.room = room;
        this.rule = rule;
    }

    public Long getGameId() {
        return gameId;
    }

    public Room getRoom() {
        return room;
    }

    public Rule getRule() {
        return rule;
    }
}
