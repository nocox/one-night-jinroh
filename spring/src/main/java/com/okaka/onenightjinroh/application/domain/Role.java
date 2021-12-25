package com.okaka.onenightjinroh.application.domain;

import java.util.HashMap;
import java.util.Map;

public abstract class Role {
    public static Long UNKNOWN_ROLE_ID = -1L;

    public Long roleId;
    public String roleName;

    public static Role byRoleId(Long roleId, String roleName) {
        if (roleId == 3) {
            return new Uranaishi(roleId, roleName);
        } else if (roleId == 4){
            return new Kaito(roleId, roleName);
        }
        return new OtherRole(roleId, roleName);
    }

    public static class Uranaishi extends Role {
        public Uranaishi(Long roleId, String roleName) {
            this.roleId = roleId;
            this.roleName = roleName;
        }
    }

    public static class Kaito extends Role {
        public Kaito(Long roleId, String roleName) {
            this.roleId = roleId;
            this.roleName = roleName;
        }
    }

    public boolean isJinroh() {
        return roleId == 2;
    }

    public static class OtherRole extends Role {

        public OtherRole(Long roleId, String roleName) {
            this.roleId = roleId;
            this.roleName = roleName;
        }
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    private static Map<Long, String> roleEngNameMap = new HashMap<Long, String>() {
        {
            put(1L, "murabito");
            put(2L, "jinroh");
            put(3L, "uranaishi");
            put(4L, "kaito");
            put(5L, "kyojin");
            put(6L, "turibito");
        }
    };

    public boolean isEngName(String engName) {
        return getRoleEngName().equals(engName);
    }

    public String getRoleEngName() {
        return roleEngNameMap.get(this.roleId);
    }

    public static Role ofByEngName(String engName) {
        switch (engName) {
            case "murabito":
                return Role.byRoleId(1L, "村人");
            case "jinroh":
                return Role.byRoleId(2L, "人狼");
            case "uranaishi":
                return Role.byRoleId(3L, "占い師");
            case "kaito":
                return Role.byRoleId(4L, "怪盗");
            case "kyojin":
                return Role.byRoleId(5L, "狂人");
            case "turibito":
                return Role.byRoleId(6L, "吊り人");
            default:
                throw new IllegalArgumentException();
        }
    }

    public boolean equals(Role role) {
        return (this.getRoleId() != null && this.getRoleId().equals(role.getRoleId())) &&
                (this.getRoleName() != null && this.getRoleName().equals(getRoleName()));
    }
}
