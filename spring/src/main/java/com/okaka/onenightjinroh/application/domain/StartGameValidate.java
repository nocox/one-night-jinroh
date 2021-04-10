package com.okaka.onenightjinroh.application.domain;

import com.okaka.jinroh.persistence.RoomParticipantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartGameValidate {

    @Autowired
    RoomParticipantDao roomParticipantDao;

    public boolean run(Long userId, Long roomId){
        Long hostUserId = roomParticipantDao.selectHostUserIdByRoom(roomId);
        if (hostUserId.equals(userId)) {
            return false;
        }
        int participantCount = roomParticipantDao.selectParticipantCount(roomId);
        if (participantCount < 3) {
            return false;
        }
        return true;
    }
}
