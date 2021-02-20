package com.okaka.onenightjinroh.application.service.room;

import com.okaka.jinroh.persistence.RoleEntity;

import java.util.List;
import java.util.stream.Collectors;

public class GameStartWebSocketBean {
    private List<RoleBean> roleList;
    private int playerCount;
    private Long gameId;

    public GameStartWebSocketBean(List<RoleEntity> roleEntityList, int playerCount, Long gameId) {
        this.roleList = roleEntityList.stream().map(RoleBean::new).collect(Collectors.toList());
        this.playerCount = playerCount;
        this.gameId = gameId;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public Long getGameId() {
        return gameId;
    }

    public List<RoleBean> getRoleList() {
        return roleList;
    }
}
