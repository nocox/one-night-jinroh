package com.okaka.onenightjinroh.application.domaimpl;

import com.okaka.jinroh.persistence.RoomDao;
import com.okaka.jinroh.persistence.RoomEntity;
import com.okaka.jinroh.persistence.RuleDao;
import com.okaka.onenightjinroh.application.domain.Room;
import com.okaka.onenightjinroh.application.domain.RoomStatus;
import com.okaka.onenightjinroh.application.domain.Rule;
import com.okaka.onenightjinroh.application.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class RoomRepositoryImpl implements RoomRepository {
    @Autowired
    RoomDao roomDao;
    @Autowired
    RuleDao ruleDao;


    @Override
    public void save(Room room) {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.uuid = room.uuid;
        roomEntity.rule_id = null;
        roomEntity.room_status = room.status.getCode();
        roomDao.insert(roomEntity);
    }

    @Override
    public Optional<Room> findByUUID(String uuid) {
        Optional<RoomEntity> entity = roomDao.selectRoomByUUID(uuid);
        return entity.map(it -> new Room(
                it.room_id,
                it.uuid,
                new Rule(it.rule_id, null),
                dbToDomainRoomStatus(it.room_status)
        ));
    }

    private RoomStatus dbToDomainRoomStatus(String strRoomStatus) {
        switch (strRoomStatus) {
            case "Ready":
                return RoomStatus.Ready;
            case "InGame":
                return RoomStatus.InGame;
            case "Finished":
                return RoomStatus.Finished;
            default:
                throw new IllegalArgumentException();
        }
    }
}
