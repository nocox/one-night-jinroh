package com.okaka.onenightjinroh.application.domain;


public class ApparentRole extends Role{
    static Long UNKNOWN_ROLE_ID = -1L;

    public ApparentRole(Long roleId, String name) {
        super(roleId);
        this.roleName = name;
    }

    static public ApparentRole createUnknownRole() {
        return new ApparentRole(UNKNOWN_ROLE_ID, "不明");
    }
}
