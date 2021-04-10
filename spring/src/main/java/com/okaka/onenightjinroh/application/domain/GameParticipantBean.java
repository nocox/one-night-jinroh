package com.okaka.onenightjinroh.application.domain;

import com.okaka.onenightjinroh.application.service.room.RoleBean;

public class GameParticipantBean {
    private Long id;
    private String name;
    private RoleBean role;
    private Boolean hostFlag;

    public GameParticipantBean(GameParticipant gameParticipant) {
        User user = gameParticipant.getUser();
        this.id = gameParticipant.getGameParticipationId();
        this.name = user.getUserName();
        this.role = new RoleBean(gameParticipant.getRole());
        this.hostFlag = gameParticipant.isHostFlg();
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
    public Boolean getHostFlag() {
        return hostFlag;
    }
}
