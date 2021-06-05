package com.okaka.onenightjinroh.application.service.night;

import com.okaka.jinroh.persistence.GameDao;
import com.okaka.jinroh.persistence.GameEntity;
import com.okaka.jinroh.persistence.GameParticipationDao;
import com.okaka.jinroh.persistence.GameParticipationEntity;
import com.okaka.jinroh.persistence.RoomEntity;
import com.okaka.onenightjinroh.application.domain.ExistRoomValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetGamePersonalUseCase {

    @Autowired
    ExistRoomValidate existRoomValidate;

    @Autowired
    GameDao gameDao;

    @Autowired
    GameParticipationDao gameParticipationDao;

    public GamePersonalBean get(String uuid, Long userId) {
        RoomEntity roomEntity = existRoomValidate.existRoom(uuid).orElseThrow(IllegalArgumentException::new);

        GameEntity gameEntity = gameDao.selectByRoomId(roomEntity.room_id);
        GameParticipationEntity gameParticipationEntity = gameParticipationDao.selectGameParticipant(gameEntity.game_id, userId);

        return new GamePersonalBean(gameEntity.game_id, gameParticipationEntity.game_participation_id);
    }
}
