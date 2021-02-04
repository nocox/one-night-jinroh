package com.okaka.onenightjinroh.application.service.top;

import com.okaka.jinroh.persistence.RoomParticipant;
import com.okaka.jinroh.persistence.RoomParticipantDao;
import com.okaka.jinroh.persistence.User;
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
        User user = new User();
        user.user_name = "プレイヤー" + (userCount + 1);
        userDao.insert(user);

        RoomParticipant roomParticipant = new RoomParticipant();
        roomParticipant.room_id = roomId;
        roomParticipant.user_id = user.user_id;
        roomParticipant.host_flg = false;
        roomParticipantDao.insert(roomParticipant);

        return new JoinedRoomUseCaseDto(user);
    }
}
