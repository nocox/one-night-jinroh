package com.okaka.onenightjinroh.application.service.top;

import com.okaka.jinroh.persistence.UserEntity;
import com.okaka.onenightjinroh.application.domain.Room;

public class CreateRoomUseCaseDto {
    private UserEntity userEntity;
    private Room room;

    public CreateRoomUseCaseDto(UserEntity userEntity, Room room) {
        this.userEntity = userEntity;
        this.room = room;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public Room getRoom() {
        return room;
    }
}
