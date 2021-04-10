package com.okaka.onenightjinroh.application.service.top;

import com.okaka.jinroh.persistence.UserEntity;

public class JoinedRoomUseCaseDto {
    private UserEntity userEntity;

    public JoinedRoomUseCaseDto(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }
}
