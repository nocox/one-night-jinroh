package com.okaka.onenightjinroh.application.service.top;

import com.okaka.jinroh.persistence.Room;
import com.okaka.jinroh.persistence.User;

public class CreateRoomUseCaseDto {
    private User user;
    private Room room;

    public CreateRoomUseCaseDto(User user, Room room) {
        this.user = user;
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public Room getRoom() {
        return room;
    }
}
