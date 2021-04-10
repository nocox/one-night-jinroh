package com.okaka.onenightjinroh.application.service.room;

import com.okaka.jinroh.persistence.RoleEntity;
import com.okaka.onenightjinroh.application.domain.Role;

public class RoleBean {
    private Long roleId;
    private String roleName;

    public RoleBean(Role role) {
        this.roleId = role.getRoleId();
        this.roleName = role.getRoleName();
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }
}
