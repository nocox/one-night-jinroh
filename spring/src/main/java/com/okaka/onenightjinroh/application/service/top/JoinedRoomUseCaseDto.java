package com.okaka.onenightjinroh.application.service.top;

import com.okaka.jinroh.persistence.User;

public class JoinedRoomUseCaseDto {
    private User user;

    public JoinedRoomUseCaseDto(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
