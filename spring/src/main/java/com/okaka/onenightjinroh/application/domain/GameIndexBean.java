package com.okaka.onenightjinroh.application.domain;

import com.okaka.onenightjinroh.application.service.room.RoleBean;

import java.util.List;
import java.util.stream.Collectors;

public class GameIndexBean {
    private String playerName;
    private RoleBean playerRole;
    private List<GameParticipantBean> otherPlayerList;

    public GameIndexBean(List<GameParticipant> gameParticipants, Long gameParticipantId) {
        List<GameParticipantBean> gameParticipantsBean = gameParticipants.stream()
                .filter(domain -> domain.getGameParticipationId().equals(gameParticipantId) == false)
                .map(GameParticipantBean::new)
                .collect(Collectors.toList());
        GameParticipantBean player = gameParticipants.stream()
                .filter(domain -> domain.getGameParticipationId().equals(gameParticipantId))
                .map(GameParticipantBean::new)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("例外"));
        this.playerName = player.getName();
        this.playerRole = player.getRole();
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
}
