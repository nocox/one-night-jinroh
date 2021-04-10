package com.okaka.onenightjinroh.application.domain;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TallyResult {
    Game game;
    GameParticipant gameParticipant;
    Integer voteCount;
    Boolean selected;

    public TallyResult(Game game, GameParticipant gameParticipant, Integer voteCount, Boolean selected) {
        this.game = game;
        this.gameParticipant = gameParticipant;
        this.voteCount = voteCount;
        this.selected = selected;
    }

    public static Integer countVote(Long gameParticipantId, List<Vote> votes) {
        long count = votes.stream()
                .filter(vote -> vote.getToGameParticipationId().equals(gameParticipantId))
                .count();
        return (int) count;
    }

    public static List<Long> selectedParticipant(List<Vote> votes) {
        Map<Long, Long> tallyResult = votes.stream()
                .collect(Collectors.groupingBy(Vote::getToGameParticipationId, Collectors.counting()));
        Long maxCount = tallyResult.values().stream()
                .max(Comparator.comparingLong(value1 -> value1))
                .orElseThrow();
        return tallyResult.entrySet().stream()
                .filter(entry -> entry.getValue().equals(maxCount))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public Game getGame() {
        return game;
    }

    public GameParticipant getGameParticipant() {
        return gameParticipant;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public Boolean getSelected() {
        return selected;
    }
}
