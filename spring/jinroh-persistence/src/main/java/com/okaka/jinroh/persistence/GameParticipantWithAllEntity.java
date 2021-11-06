package com.okaka.jinroh.persistence;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

@Entity
public class GameParticipantWithAllEntity {

    @Column(name = "GAME_PARTICIPATION_ID")
    private Long gameParticipationId;

    @Column(name = "GAME_ID")
    private Long gameId;

    @Column(name = "ROOM_ID")
    private Long roomId;

    @Column(name = "RULE_ID")
    private Long ruleId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "ROLE_ID")
    private Long roleId;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @Column(name = "HOST_FLG")
    private boolean hostFlg;

    public Long getGameParticipationId() {
        return gameParticipationId;
    }

    public Long getGameId() {
        return gameId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public boolean isHostFlg() {
        return hostFlg;
    }
}