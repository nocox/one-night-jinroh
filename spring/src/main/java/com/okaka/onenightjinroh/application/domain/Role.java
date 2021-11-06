package com.okaka.onenightjinroh.application.domain;

public abstract class Role {
    static Long UNKNOWN_ROLE_ID = -1L;

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
}
