package com.okaka.onenightjinroh.application.bean;

import com.okaka.onenightjinroh.application.service.room.RoleBean;

import java.util.List;

public class NightUranaiResultBean {
    private String status;
    private Long participantId;
    private List<RoleBean> roles;
    private UserBean user;

    public NightUranaiResultBean(String status, Long participantId, List<RoleBean> roles, UserBean userBean) {
        this.status = status;
        this.participantId = participantId;
        this.roles = roles;
        this.user = userBean;
    }

    public String getStatus() {
        return status;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public List<RoleBean> getRoles() {
        return roles;
    }

    public UserBean getUser() {
        return user;
    }

    public static class UserBean {
        private Long userId;
        private String userName;

        public Long getUserId() {
            return userId;
        }

        public String getUserName() {
            return userName;
        }

        public UserBean(Long userId, String userName) {
            this.userId = userId;
            this.userName = userName;
        }
    }
}
