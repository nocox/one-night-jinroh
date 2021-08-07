package com.okaka.onenightjinroh.application.service.night.dto;

import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.domain.User;
import com.okaka.onenightjinroh.application.service.night.NightUranaiStatus;

import java.util.List;

public class NightUranaiResultDto {
    NightUranaiStatus status;
    List<Role> roles;
    Long selectedPlayer;
    User user;

    public NightUranaiResultDto(List<Role> roles, NightUranaiStatus status, Long selectedPlayer, User user) {
        this.status = status;
        this.roles = roles;
        this.selectedPlayer = selectedPlayer;
        this.user = user;
    }

    public NightUranaiStatus getStatus() {
        return status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public Long getSelectedPlayer() {
        return selectedPlayer;
    }

    public User getUser() {
        return user;
    }
}
