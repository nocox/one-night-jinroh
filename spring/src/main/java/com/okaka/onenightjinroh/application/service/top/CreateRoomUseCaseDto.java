package com.okaka.onenightjinroh.application.service.top;

import com.okaka.jinroh.persistence.RoomEntity;
import com.okaka.jinroh.persistence.UserEntity;

public class CreateRoomUseCaseDto {
    private UserEntity userEntity;
    private RoomEntity roomEntity;

    public CreateRoomUseCaseDto(UserEntity userEntity, RoomEntity roomEntity) {
        this.userEntity = userEntity;
        this.roomEntity = roomEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public RoomEntity getRoomEntity() {
        return roomEntity;
    }
}
