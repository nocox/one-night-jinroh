package com.okaka.onenightjinroh.api;

import com.okaka.jinroh.persistence.GameDao;
import com.okaka.jinroh.persistence.GameEntity;
import com.okaka.jinroh.persistence.GameParticipationDao;
import com.okaka.jinroh.persistence.GameParticipationEntity;
import com.okaka.jinroh.persistence.NightActDao;
import com.okaka.jinroh.persistence.RoomEntity;
import com.okaka.onenightjinroh.application.domain.ExistRoomValidate;
import com.okaka.onenightjinroh.application.service.night.DoneNightTermActUseCase;
import com.okaka.onenightjinroh.application.service.night.GetNightTermIndexUseCase;
import com.okaka.onenightjinroh.application.service.night.NightTermIndexBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class NightController {
    @Autowired
    HttpSession session;

    @Autowired
    ExistRoomValidate existRoomValidate;

    @Autowired
    GetNightTermIndexUseCase getNightTermIndexUseCase;

    @Autowired
    DoneNightTermActUseCase doneNightTermActUseCase;

    @Autowired
    GameDao gameDao;

    @Autowired
    GameParticipationDao gameParticipationDao;

    @Autowired
    NightActDao nightActDao;

    @RequestMapping(path = "/night-index")
    @CrossOrigin(origins = {"http://ec2-52-198-98-214.ap-northeast-1.compute.amazonaws.com"}, allowCredentials = "true")
    NightTermIndexBean getNightTermIndex() {
        String uuid = session.getAttribute("room_uuid").toString();
        String strUserId = session.getAttribute("user_id").toString();
        Long userId = Long.valueOf(strUserId);

        RoomEntity roomEntity = existRoomValidate.existRoom(uuid).orElseThrow(IllegalArgumentException::new);

        // TODO: 最新のgameを取得する必要がある．
        GameEntity gameEntity = gameDao.selectByRoomId(roomEntity.room_id);
        GameParticipationEntity gameParticipationEntity = gameParticipationDao.selectGameParticipant(gameEntity.game_id, userId);

        session.setAttribute("game_id", gameEntity.game_id);
        session.setAttribute("game_participation_id", gameParticipationEntity.game_participation_id);

        return getNightTermIndexUseCase.get(gameEntity.game_id, gameParticipationEntity.game_participation_id);
    }

    @RequestMapping(path = "/done-night-act")
    @CrossOrigin(origins = {"http://ec2-52-198-98-214.ap-northeast-1.compute.amazonaws.com"}, allowCredentials = "true")
    int doneNightTermAct() {
        String strGameId = session.getAttribute("game_id").toString();
        Long gameId = Long.valueOf(strGameId);

        String strGameParticipationId = session.getAttribute("game_participation_id").toString();
        Long gameParticipantId = Long.valueOf(strGameParticipationId);
        doneNightTermActUseCase.done(gameParticipantId, gameId);

        return 0;
    }
}
