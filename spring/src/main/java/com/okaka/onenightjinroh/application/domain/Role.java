package com.okaka.onenightjinroh.application.domain;

public class Role {
    public Long roleId;
    public String roleName;

    public Role(Long roleId) {
        this.roleId = roleId;
        this.roleName = null;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
