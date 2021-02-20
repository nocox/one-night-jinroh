package com.okaka.onenightjinroh.application.service.top;

import com.okaka.jinroh.persistence.RoomParticipant;
import com.okaka.jinroh.persistence.RoomParticipantDao;
import com.okaka.jinroh.persistence.UserEntity;
import com.okaka.jinroh.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinedRoomUseCase {
    @Autowired
    UserDao userDao;

    @Autowired
    RoomParticipantDao roomParticipantDao;

    public JoinedRoomUseCaseDto joinedRoom(Long roomId) {
        int userCount = userDao.selectCount();
        UserEntity userEntity = new UserEntity();
        userEntity.user_name = "プレイヤー" + (userCount + 1);
        userDao.insert(userEntity);

        RoomParticipant roomParticipant = new RoomParticipant();
        roomParticipant.room_id = roomId;
        roomParticipant.user_id = userEntity.user_id;
        roomParticipant.host_flg = false;
        roomParticipantDao.insert(roomParticipant);

        return new JoinedRoomUseCaseDto(userEntity);
    }
}
