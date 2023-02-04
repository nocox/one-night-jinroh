package com.okaka.jinroh.persistence;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;


@Entity
@Table(name = "CLS_ROOM_STATUS")
public class ClsRoomStatusEntity {

    @Id
    @Column(name = "room_status")
    public String room_status;

    public ClsRoomStatusEntity() {
    }

    public ClsRoomStatusEntity(String room_status) {
        this.room_status = room_status;
    }

    public String getRoom_status() {
        return room_status;
    }
}
