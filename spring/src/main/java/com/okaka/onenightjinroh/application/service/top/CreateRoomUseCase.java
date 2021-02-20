package com.okaka.onenightjinroh.application.service.top;

import com.okaka.jinroh.persistence.RoomEntity;
import com.okaka.jinroh.persistence.RoomDao;
import com.okaka.jinroh.persistence.RoomParticipant;
import com.okaka.jinroh.persistence.RoomParticipantDao;
import com.okaka.jinroh.persistence.UserEntity;
import com.okaka.jinroh.persistence.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CreateRoomUseCase {
    private final RoomDao roomDao;
    private final UserDao userDao;
    private final RoomParticipantDao roomParticipantDao;

    public CreateRoomUseCase(RoomDao roomDao, UserDao userDao, RoomParticipantDao roomParticipantDao) {
        this.roomDao = roomDao;
        this.userDao = userDao;
        this.roomParticipantDao = roomParticipantDao;
    }

    @Transactional
    public CreateRoomUseCaseDto createRoom(){
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.uuid = UUID.randomUUID().toString();
        roomEntity.rule_id = null;
        roomDao.insert(roomEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.user_name = "ホスト";
        userDao.insert(userEntity);

        RoomParticipant roomParticipant = new RoomParticipant();
        roomParticipant.room_id = roomEntity.room_id;
        roomParticipant.user_id = userEntity.user_id;
        roomParticipant.host_flg = true;
        roomParticipantDao.insert(roomParticipant);

        return new CreateRoomUseCaseDto(userEntity, roomEntity);
    }
}
