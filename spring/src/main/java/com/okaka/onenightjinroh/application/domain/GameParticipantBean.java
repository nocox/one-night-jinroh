package com.okaka.onenightjinroh.application.domain;

import com.okaka.onenightjinroh.application.service.room.RoleBean;

public class GameParticipantBean {
    private Long id;
    private String name;
    private RoleBean role;

    public GameParticipantBean(GameParticipant gameParticipant) {
        User user = gameParticipant.getUser();
        this.id = user.getUserId();
        this.name = user.getUserName();
        this.role = new RoleBean(gameParticipant.getRole());
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public RoleBean getRole() {
        return role;
    }
}
