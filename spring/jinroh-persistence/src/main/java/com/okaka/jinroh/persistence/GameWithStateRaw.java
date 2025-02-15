package com.okaka.jinroh.persistence;

import org.seasar.doma.Entity;

@Entity
public class GameWithStateRaw {
    Long gameId;
    Long roomId;
    String gameTerm;
    Long ruleId;
    String ruleName;
    Long roleId;
    String roleName;

    public Long getGameId() {
        return gameId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRuleName() {
        return ruleName;
    }

    public String getGameTerm() {
        return gameTerm;
    }
}