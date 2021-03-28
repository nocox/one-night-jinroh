package com.okaka.onenightjinroh.application.service.tally;

import java.util.List;

public class TallyResultBean {
    private List<GameParticipantWithVoteBean> selectedPlayers;
    private List<GameParticipantWithVoteBean> players;

    public List<GameParticipantWithVoteBean> getSelectedPlayers() {
        return selectedPlayers;
    }
    public List<GameParticipantWithVoteBean> getPlayers() {
        return players;
    }

    public void setSelectedPlayers(List<GameParticipantWithVoteBean> selectedPlayers) {
        this.selectedPlayers = selectedPlayers;
    }

    public void setPlayers(List<GameParticipantWithVoteBean> players) {
        this.players = players;
    }


}
