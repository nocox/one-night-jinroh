package com.okaka.onenightjinroh;

import java.util.List;
import java.util.stream.Collectors;

public class GameStartBean {
    private List<RoleBean> roleList;
    private int playerCount;
    private Long gameId;

    public GameStartBean(List<Role> roleList, int playerCount, Long gameId) {
        this.roleList = roleList.stream().map(RoleBean::new).collect(Collectors.toList());
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
