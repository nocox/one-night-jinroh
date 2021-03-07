package com.okaka.onenightjinroh.application.service.tally;

import java.util.List;

public class TallyResultBean {
    private GameParticipantWithVoteBean selectedPlayer;
    private List<GameParticipantWithVoteBean> players;

    public GameParticipantWithVoteBean getSelectedPlayer() {
        return selectedPlayer;
    }

    public List<GameParticipantWithVoteBean> getPlayers() {
        return players;
    }

    public void setSelectedPlayer(GameParticipantWithVoteBean selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }

    public void setPlayers(List<GameParticipantWithVoteBean> players) {
        this.players = players;
    }


}
