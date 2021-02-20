package com.okaka.onenightjinroh.application.service.night;

import com.okaka.jinroh.persistence.GameParticipationEntity;
import com.okaka.jinroh.persistence.RoleEntity;
import com.okaka.jinroh.persistence.UserEntity;
import com.okaka.onenightjinroh.application.service.room.RoleBean;

import java.util.List;
import java.util.stream.Collectors;

public class NightTermIndexBean {
    private Long gameId;
    private String playerName;
    private RoleBean playerRole;
    private List<GamePlayerBean> otherPlayerList;

    public NightTermIndexBean(Long playerId, List<RoleEntity> roleEntityList, List<GameParticipationEntity> gameParticipationEntityList, List<UserEntity> userEntityList, Long gameId) {
        this.gameId = gameId;
        this.playerName = userEntityList.stream()
                .filter(user -> user.user_id == playerId)
                .findFirst().get().user_name;
        this.playerRole = gameParticipationEntityList.stream()
                .filter(gameParticipation -> gameParticipation.user_id.equals(playerId))
                .map(gameParticipation -> {
                    return roleEntityList.stream()
                            .filter(role -> role.role_id.equals(gameParticipation.role_id))
                            .findFirst().get();
                })
                .map(RoleBean::new).findFirst().get();
        this.otherPlayerList = gameParticipationEntityList.stream()
                .filter(gameParticipation -> gameParticipation.user_id.equals(playerId) == false)
                .map(gameParticipation -> {
                    RoleEntity playerRoleEntity = roleEntityList.stream()
                            .filter(role -> role.role_id.equals(gameParticipation.role_id))
                            .findFirst().get();
                    UserEntity playerInfo = userEntityList.stream()
                            .filter(user -> user.user_id == gameParticipation.user_id)
                            .findFirst().get();
                    return new GamePlayerBean(playerInfo, playerRoleEntity);
                }).collect(Collectors.toList());
    }

    public String getPlayerName() {
        return playerName;
    }

    public RoleBean getPlayerRole() {
        return playerRole;
    }

    public Long getGameId() {
        return gameId;
    }

    public List<GamePlayerBean> getOtherPlayerList() {
        return otherPlayerList;
    }
    public class GamePlayerBean {
        private Long id;
        private String name;

        private RoleBean role;

        public GamePlayerBean(UserEntity userEntity, RoleEntity roleEntity) {
            this.id = userEntity.user_id;
            this.name = userEntity.user_name;
            this.role = new RoleBean(roleEntity);
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
