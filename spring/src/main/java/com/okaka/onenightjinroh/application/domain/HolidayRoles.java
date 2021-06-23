package com.okaka.onenightjinroh.application.domain;

import java.util.List;

public class HolidayRoles {
    Long gameId;
    List<Role> roles;

    public HolidayRoles(Long gameId, List<Role> roles) {
        this.gameId = gameId;
        this.roles = roles;
    }

    public Long getGameId() {
        return gameId;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
