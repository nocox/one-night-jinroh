package com.okaka.onenightjinroh.application.domain;

public class Role {
    public Long roleId;
    public String roleName;

    public Role(Long roleId) {
        this.roleId = roleId;
        this.roleName = null;
    }
}
