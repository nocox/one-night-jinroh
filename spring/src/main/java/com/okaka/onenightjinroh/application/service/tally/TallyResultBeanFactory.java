package com.okaka.onenightjinroh.application.service.tally;

import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.GameParticipantBean;
import com.okaka.onenightjinroh.application.domain.GameParticipantRepository;
import com.okaka.onenightjinroh.application.domain.Vote;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TallyResultBeanFactory {


    public static TallyResultBean create(List<Vote> voteList , List<GameParticipant> gameParticipantList) {
        Map<Long, Long> tallyResult = voteList.stream()
                .collect(Collectors.groupingBy(Vote::getToGameParticipationId, Collectors.counting()));
        Long selectedId = voteList.stream()
                .collect(Collectors.groupingBy(Vote::getToGameParticipationId, Collectors.counting()))
                .entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .orElseThrow()
                .getKey();

        Map<Long, GameParticipantWithVoteBean> gameParticipantWithVoteBeanMap = GameParticipantRepository
                .toMapGameParticipant(gameParticipantList).entrySet().stream()
                .map(v -> new GameParticipantWithVoteBean(v.getValue(), tallyResult.getOrDefault(v.getKey(), 0L)))
                .collect(Collectors.toMap(GameParticipantBean::getId, v -> v));

        TallyResultBean tallyResultBean = new TallyResultBean();
        tallyResultBean.setSelectedPlayer(gameParticipantWithVoteBeanMap.get(selectedId));
        tallyResultBean.setPlayers(new ArrayList<>(gameParticipantWithVoteBeanMap.values()));
        return tallyResultBean;
    }
}
