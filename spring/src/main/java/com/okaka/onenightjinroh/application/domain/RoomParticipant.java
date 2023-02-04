package com.okaka.onenightjinroh.application.domain;

public class RoomParticipant {
    public Long roomId;
    public Long userId;
    public boolean hostFlg;

    public RoomParticipant(Long roomId, Long userId, boolean hostFlg) {
        this.roomId = roomId;
        this.userId = userId;
        this.hostFlg = hostFlg;
    }
}
