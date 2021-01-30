package com.okaka.onenightjinroh;

import java.util.List;
import java.util.stream.Collectors;

public class RoomBean {
    private String uuid;
    private List<UserBean> userList;
    private boolean hostFlg;

    public RoomBean(Room room, List<User> userList, boolean hostFlg, Long hostUserId) {
        this.uuid = room.uuid;
        this.userList = userList.stream().map(user -> new UserBean(user, hostUserId)).collect(Collectors.toList());
        this.hostFlg = hostFlg;
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
