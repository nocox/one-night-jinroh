package com.okaka.onenightjinroh.application.service.room;

import com.okaka.jinroh.persistence.RoleEntity;

public class RoleBean {
    private Long roleId;
    private String roleName;

    public RoleBean(RoleEntity roleEntity) {
        this.roleId = roleEntity.role_id;
        this.roleName = roleEntity.role_name;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }
}
