package com.okaka.onenightjinroh.application.service.tally;

import com.okaka.onenightjinroh.application.domain.TallyResult;

import java.util.List;
import java.util.stream.Collectors;

public class TallyResultBeanFactory {

    public static TallyResultBean create(List<TallyResult> tallyResults) {
        List<GameParticipantWithVoteBean> players = tallyResults.stream()
                .map(tallyResult -> new GameParticipantWithVoteBean(tallyResult.getGameParticipant(), tallyResult.getVoteCount()))
                .collect(Collectors.toList());
        List<GameParticipantWithVoteBean> selectedPlayers = tallyResults.stream()
                .filter(TallyResult::getSelected)
                .map(tallyResult -> new GameParticipantWithVoteBean(tallyResult.getGameParticipant(), tallyResult.getVoteCount()))
                .collect(Collectors.toList());
        boolean peacefulFlag = tallyResults.stream()
                .allMatch(tallyResult -> tallyResult.getVoteCount() == 1 );

        TallyResultBean tallyResultBean = new TallyResultBean(selectedPlayers, players, peacefulFlag);
        // tallyResultBean.setPlayers(players);
        // tallyResultBean.setSelectedPlayers(selectedPlayers);

        return tallyResultBean;
    }
}
