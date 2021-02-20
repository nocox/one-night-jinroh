package com.okaka.onenightjinroh.application.service.room;

import com.okaka.jinroh.persistence.RoomEntity;
import com.okaka.jinroh.persistence.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class RoomIndexBean {
    private String uuid;
    private List<UserBean> userList;
    private boolean hostFlg;

    public RoomIndexBean(RoomEntity roomEntity, List<UserEntity> userEntityList, Long userId, Long hostUserId) {
        this.uuid = roomEntity.uuid;
        this.userList = userEntityList.stream().map(user -> new UserBean(user, hostUserId)).collect(Collectors.toList());
        this.hostFlg = userId.equals(hostUserId);
    }

    public String getUuid() {
        return uuid;
    }

    public List<UserBean> getUserList() {
        return userList;
    }

    public boolean isHostFlg() {
        return hostFlg;
    }

    public class UserBean {
        private Long userId;
        private String name;
        private boolean hostFlg;

        public UserBean(UserEntity userEntity, Long hostUserId) {
            this.userId = userEntity.user_id;
            this.name = userEntity.user_name;
            this.hostFlg = userEntity.user_id.equals(hostUserId);
        }

        public Long getUserId() {
            return userId;
        }

        public String getName() {
            return name;
        }

        public boolean isHostFlg() {
            return hostFlg;
        }
    }
}