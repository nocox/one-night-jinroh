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

    public String getRoleEngName() {
        return roleEngNameMap.get(this.roleId);
    }
}
