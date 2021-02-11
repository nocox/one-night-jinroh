package com.okaka.onenightjinroh.application.service.room;

import com.okaka.jinroh.persistence.Role;

public class RoleBean {
    private Long roleId;
    private String roleName;

    public RoleBean(Role role) {
        this.roleId = role.role_id;
        this.roleName = role.role_name;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }
}
