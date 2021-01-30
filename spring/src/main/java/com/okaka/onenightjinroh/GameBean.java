package com.okaka.onenightjinroh;

import com.okaka.jinroh.persistence.GameParticipation;
import com.okaka.jinroh.persistence.Role;
import com.okaka.jinroh.persistence.User;

import java.util.List;
import java.util.stream.Collectors;

public class GameBean {
    private String playerName;
    private RoleBean playerRole;
    private List<GamePlayerBean> otherPlayerList;

    public GameBean(Long playerId, List<Role> roleList, List<GameParticipation> gameParticipationList, List<User> userList) {
        this.playerName = userList.stream()
                .filter(user -> user.user_id == playerId)
                .findFirst().get().user_name;
        this.playerRole = gameParticipationList.stream()
                .filter(gameParticipation -> gameParticipation.user_id.equals(playerId))
                .map(gameParticipation -> {
                    return roleList.stream()
                            .filter(role -> role.role_id.equals(gameParticipation.role_id))
                            .findFirst().get();
                })
                .map(RoleBean::new).findFirst().get();
        this.otherPlayerList = gameParticipationList.stream()
                .filter(gameParticipation -> gameParticipation.user_id.equals(playerId) == false)
                .map(gameParticipation -> {
                    Role playerRole = roleList.stream()
                            .filter(role -> role.role_id.equals(gameParticipation.role_id))
                            .findFirst().get();
                    User playerInfo = userList.stream()
                            .filter(user -> user.user_id == gameParticipation.user_id)
                            .findFirst().get();
                    return new GamePlayerBean(playerInfo, playerRole);
                }).collect(Collectors.toList());
    }

    public String getPlayerName() {
        return playerName;
    }

    public RoleBean getPlayerRole() {
        return playerRole;
    }

    public List<GamePlayerBean> getOtherPlayerList() {
        return otherPlayerList;
    }

    public class GamePlayerBean {
        private Long id;
        private String name;
        private RoleBean role;

        public GamePlayerBean(User user, Role role) {
            this.id = user.user_id;
            this.name = user.user_name;
            this.role = new RoleBean(role);
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public RoleBean getRole() {
            return role;
        }
    }

}
