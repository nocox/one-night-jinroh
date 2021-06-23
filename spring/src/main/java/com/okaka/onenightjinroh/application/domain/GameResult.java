package com.okaka.onenightjinroh.application.domain;

import java.util.List;

public class GameResult {
    private final Long gameId;
    private final Long gameParticipantId;
    private final Judge judge;
    private final List<GameParticipant> gameParticipants;
    private final HolidayRoles holidayRoles;

    public GameResult(Long gameId, Long gameParticipantId, Judge judge, List<GameParticipant> gameParticipants, HolidayRoles holidayRoles) {
        this.gameId = gameId;
        this.gameParticipantId = gameParticipantId;
        this.judge = judge;
        this.gameParticipants = gameParticipants;
        this.holidayRoles = holidayRoles;
    }

    public Long getGameId() {
        return gameId;
    }

    public Long getGameParticipantId() {
        return gameParticipantId;
    }

    public Judge getJudge() {
        return judge;
    }

    public List<GameParticipant> getGameParticipants() {
        return gameParticipants;
    }

    public HolidayRoles getHolidayRoles() {
        return holidayRoles;
    }
}
