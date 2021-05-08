package com.okaka.onenightjinroh.application.domain;

import com.okaka.onenightjinroh.application.service.room.RoleBean;

import java.util.List;
import java.util.stream.Collectors;

public class GameIndexBean {
    private String playerName;
    private RoleBean playerRole;
    private Boolean hostFlag;
    private List<GameParticipantBean> otherPlayerList;

    public GameIndexBean(List<GameParticipant> gameParticipants, Long gameParticipantId) {
        List<GameParticipantBean> gameParticipantsBean = gameParticipants.stream()
                .filter(domain -> domain.getGameParticipationId().equals(gameParticipantId) == false)
                .map(GameParticipant::setUnknownRole)
                .map(GameParticipantBean::new)
                .collect(Collectors.toList());
        GameParticipantBean player = gameParticipants.stream()
                .filter(domain -> domain.getGameParticipationId().equals(gameParticipantId))
                .map(GameParticipantBean::new)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("例外"));
        this.playerName = player.getName();
        this.playerRole = player.getRole();
        this.hostFlag = player.getHostFlag();
        this.otherPlayerList = gameParticipantsBean;
    }

    public String getPlayerName() {
        return playerName;
    }

    public RoleBean getPlayerRole() {
        return playerRole;
    }

    public List<GameParticipantBean> getOtherPlayerList() {
        return otherPlayerList;
    }

    public Boolean getHostFlag() {
        return hostFlag;
    }
}
