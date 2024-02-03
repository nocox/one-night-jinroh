package com.okaka.onenightjinroh.domaimpl;

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
        // TODO: これやめたい。。そもそもRuleはこいつが持ってなくて良いので、修正したい。
        Rule rule = room.getRule();
        if (rule !=null) {
            roomEntity.rule_id = rule.getRuleId();
        } else {
            roomEntity.rule_id = null;
        }
        roomEntity.room_status = room.status.getCode();

        Optional<RoomEntity> optRoom = roomDao.selectRoomByUUID(room.uuid);
        if (optRoom.isPresent()) {
            roomEntity.room_id = room.getRoomId();
            roomDao.update(roomEntity);
        } else {
            roomDao.insert(roomEntity);
        }
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
