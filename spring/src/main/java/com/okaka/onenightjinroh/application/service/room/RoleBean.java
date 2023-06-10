package com.okaka.onenightjinroh.application.service.room;

import com.okaka.onenightjinroh.application.domain.Role;

public class RoleBean {
    private Long roleId;
    private String roleName;

    public RoleBean(Role role) {
        this.roleId = role.getRoleId();
        this.roleName = role.getRoleName();
    }

    public RoleBean(Long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public static RoleBean ofUnknownRole() {
        return new RoleBean(Role.UNKNOWN_ROLE_ID, "不明");
    }

    public static RoleBean ofChange(Role fromRole, Role toRole) {
        return new RoleBean(fromRole.getRoleId() + 10, toRole.getRoleName());
    }

    public static RoleBean ofChangedKaito(Role role) {
        return new RoleBean(role.getRoleId() + 10, "怪盗");
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }
}
