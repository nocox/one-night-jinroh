package com.okaka.onenightjinroh.application.domain;

public class User {
    public Long userId;
    public String userName;

    public User(Long userId) {
        this.userId = userId;
        this.userName = null;
    }
}
