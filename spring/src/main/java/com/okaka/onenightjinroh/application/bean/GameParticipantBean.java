package com.okaka.onenightjinroh.application.bean;

import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.domain.User;
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

    public GameParticipantBean(Long id, String name, RoleBean role, Boolean hostFlag) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.hostFlag = hostFlag;
    }

    public static GameParticipantBean ofRoleOpen(GameParticipant gameParticipant) {
        User user = gameParticipant.getUser();
        return new GameParticipantBean(
                gameParticipant.getGameParticipationId(),
                user.getUserName(),
                new RoleBean(gameParticipant.getRole()),
                gameParticipant.isHostFlg()
        );
    }

    public static GameParticipantBean ofRoleHidden(GameParticipant gameParticipant) {
        User user = gameParticipant.getUser();
        return new GameParticipantBean(
                gameParticipant.getGameParticipationId(),
                user.getUserName(),
                RoleBean.ofUnknownRole(),
                gameParticipant.isHostFlg()
        );
    }

    public static GameParticipantBean ofChangedRole(GameParticipant gameParticipant, Role role) {
        User user = gameParticipant.getUser();
        return new GameParticipantBean(
                gameParticipant.getGameParticipationId(),
                user.getUserName(),
                RoleBean.ofChange(gameParticipant.getRole(), role),
                gameParticipant.isHostFlg()
        );
    }

    public static GameParticipantBean ofChangedKaito(GameParticipant gameParticipant) {
        User user = gameParticipant.getUser();
        return new GameParticipantBean(
                gameParticipant.getGameParticipationId(),
                user.getUserName(),
                RoleBean.ofChangedKaito(gameParticipant.getRole()),
                gameParticipant.isHostFlg()
        );
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
