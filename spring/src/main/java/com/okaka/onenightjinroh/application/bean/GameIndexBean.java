package com.okaka.onenightjinroh.application.bean;

import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.service.room.RoleBean;

import java.util.List;
import java.util.stream.Collectors;

public class GameIndexBean {
    private String playerName;
    private RoleBean playerRole;
    private Boolean hostFlag;
    private List<GameParticipantBean> otherPlayerList;
    private final String nightActLog;

    private GameIndexBean(String playerName, RoleBean playerRole, Boolean hostFlag, List<GameParticipantBean> otherPlayerList, String nightActLog) {
        this.playerName = playerName;
        this.playerRole = playerRole;
        this.hostFlag = hostFlag;
        this.otherPlayerList = otherPlayerList;
        this.nightActLog = nightActLog;
    }

    public static GameIndexBean ofHideRole(List<GameParticipant> gameParticipants, Long gameParticipantId) {
        List<GameParticipantBean> gameParticipantsBean = gameParticipants.stream()
                .filter(domain -> !domain.getGameParticipationId().equals(gameParticipantId))
                .map(GameParticipant::setUnknownRole)
                .map(GameParticipantBean::new)
                .collect(Collectors.toList());
        GameParticipantBean player = extractMyPlayer(gameParticipants, gameParticipantId);
        return new GameIndexBean(player.getName(), player.getRole(), player.getHostFlag(), gameParticipantsBean, null);
    }

    public static GameIndexBean ofOpenRole(List<GameParticipant> gameParticipants, Long gameParticipantId) {
        List<GameParticipantBean> gameParticipantsBean = gameParticipants.stream()
                .filter(domain -> !domain.getGameParticipationId().equals(gameParticipantId))
                .map(GameParticipantBean::new)
                .collect(Collectors.toList());
        GameParticipantBean player = extractMyPlayer(gameParticipants, gameParticipantId);

        return new GameIndexBean(player.getName(), player.getRole(), player.getHostFlag(), gameParticipantsBean, null);
    }

    public static GameIndexBean of(List<GameParticipantBean> participantBeans, Long participantId, String nightActLog) {
        GameParticipantBean myselfBean = participantBeans.stream()
                .filter(bean -> bean.getId().equals(participantId))
                .findFirst()
                .orElseThrow();

        List<GameParticipantBean> gameParticipantBeans = participantBeans.stream()
                .filter(bean -> !bean.getId().equals(participantId))
                .collect(Collectors.toList());

        return new GameIndexBean(myselfBean.getName(), myselfBean.getRole(), myselfBean.getHostFlag(), gameParticipantBeans, nightActLog);
    }

    private static GameParticipantBean extractMyPlayer(List<GameParticipant> gameParticipants, Long gameParticipantId) {
        return gameParticipants.stream()
                .filter(domain -> domain.getGameParticipationId().equals(gameParticipantId))
                .map(GameParticipantBean::new)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("例外"));
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

    public String getNightActLog() {
        return nightActLog;
    }
}
