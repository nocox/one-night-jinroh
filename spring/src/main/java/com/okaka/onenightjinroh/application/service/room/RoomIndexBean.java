package com.okaka.onenightjinroh.application.service.room;

import com.okaka.jinroh.persistence.Room;
import com.okaka.jinroh.persistence.User;

import java.util.List;
import java.util.stream.Collectors;

public class RoomIndexBean {
    private String uuid;
    private List<UserBean> userList;
    private boolean hostFlg;

    public RoomIndexBean(Room room, List<User> userList, Long userId, Long hostUserId) {
        this.uuid = room.uuid;
        this.userList = userList.stream().map(user -> new UserBean(user, hostUserId)).collect(Collectors.toList());
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

        public UserBean(User user, Long hostUserId) {
            this.userId = user.user_id;
            this.name = user.user_name;
            this.hostFlg = user.user_id.equals(hostUserId);
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
