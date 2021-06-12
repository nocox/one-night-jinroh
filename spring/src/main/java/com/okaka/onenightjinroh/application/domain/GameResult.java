package com.okaka.onenightjinroh.application.domain;

import java.util.List;

public class GameResult {
    private final Long gameId;
    private final Long gameParticipantId;
    private final Judge judge;
    private final List<GameParticipant> gameParticipants;
    private final List<Role> holidayCard;

    public GameResult(Long gameId, Long gameParticipantId, Judge judge, List<GameParticipant> gameParticipants, List<Role> holidayCard) {
        this.gameId = gameId;
        this.gameParticipantId = gameParticipantId;
        this.judge = judge;
        this.gameParticipants = gameParticipants;
        this.holidayCard = holidayCard;
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

    public List<Role> getHolidayCard() {
        return holidayCard;
    }
}
