package com.okaka.onenightjinroh.application.service.tally;

import java.util.List;

public class TallyResultBean {
    private List<GameParticipantWithVoteBean> selectedPlayers;
    private List<GameParticipantWithVoteBean> players;
    private boolean peacefulFlag;

    public TallyResultBean(List<GameParticipantWithVoteBean> selectedPlayers, List<GameParticipantWithVoteBean> players, boolean peacefulFlag){
        this.selectedPlayers = selectedPlayers;
        this.players = players;
        this.peacefulFlag = peacefulFlag;
    }

    public TallyResultBean(){}

    public List<GameParticipantWithVoteBean> getSelectedPlayers() {
        return selectedPlayers;
    }
    public List<GameParticipantWithVoteBean> getPlayers() {
        return players;
    }
    public boolean getPeacefulFlag() {
        return peacefulFlag;
    }

    public void setSelectedPlayers(List<GameParticipantWithVoteBean> selectedPlayers) {
        this.selectedPlayers = selectedPlayers;
    }

    public void setPlayers(List<GameParticipantWithVoteBean> players) {
        this.players = players;
    }
}
