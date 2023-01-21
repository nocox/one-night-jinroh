package com.okaka.onenightjinroh.application.domain;

public enum RoomStatus {
    Ready("Ready"),
    InGame("InGame"),
    Finished("Finished");

    final String code;

    RoomStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
