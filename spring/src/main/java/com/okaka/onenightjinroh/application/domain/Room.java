package com.okaka.onenightjinroh.application.domain;

public class Room {
    public Long roomId;
    public String uuid;
    public Rule rule; // FIXME: 昔はここにルーム情報をそのまま置いていたが、Room保存時に、ruleは決まらないので、考え直したい。
    public RoomStatus status;

    public Room finishRoom() {
        return new Room(
                this.getRoomId(),
                this.getUuid(),
                this.getRule(),
                RoomStatus.Finished
        );
    }

    public Room(Long roomId, String uuid, Rule rule, RoomStatus status) {
        this.roomId = roomId;
        this.uuid = uuid;
        this.rule = rule;
        this.status = status;
    }

    public Long getRoomId() {
        return roomId;
    }

    public String getUuid() {
        return uuid;
    }

    public Rule getRule() {
        return rule;
    }

    public RoomStatus getStatus() {
        return status;
    }
}
