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

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }
}
