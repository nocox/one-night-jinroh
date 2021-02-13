package com.okaka.onenightjinroh.api;

import com.okaka.jinroh.persistence.Game;
import com.okaka.jinroh.persistence.GameDao;
import com.okaka.jinroh.persistence.GameParticipation;
import com.okaka.jinroh.persistence.GameParticipationDao;
import com.okaka.jinroh.persistence.NightAct;
import com.okaka.jinroh.persistence.NightActDao;
import com.okaka.jinroh.persistence.Room;
import com.okaka.onenightjinroh.application.domain.ExistRoomValidate;
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
    GameDao gameDao;

    @Autowired
    GameParticipationDao gameParticipationDao;

    @Autowired
    NightActDao nightActDao;

    @RequestMapping(path = "/night-index")
    @CrossOrigin(origins = {"http://localhost:8081"}, allowCredentials = "true")
    NightTermIndexBean getNightTermIndex() {
        String uuid = session.getAttribute("room_uuid").toString();
        String strUserId = session.getAttribute("user_id").toString();
        Long userId = Long.valueOf(strUserId);

        Room room = existRoomValidate.existRoom(uuid).orElseThrow(IllegalArgumentException::new);

        Game game = gameDao.selectByRoomId(room.room_id);
        GameParticipation gameParticipation = gameParticipationDao.selectGameParticipant(game.game_id, userId);

        session.setAttribute("game_id", game.game_id);
        session.setAttribute("game_participation_id", gameParticipation.game_participation_id);

        return getNightTermIndexUseCase.getNightTermIndex(userId, room.room_id);
    }

    @RequestMapping(path = "/done-night-act")
    @CrossOrigin(origins = {"http://localhost:8081"}, allowCredentials = "true")
    int doneNightTermAct() {
        String strGameParticipationId = session.getAttribute("game_participation_id").toString();
        Long gameParticipantId = Long.valueOf(strGameParticipationId);

        NightAct nightAct = new NightAct();
        nightAct.game_participation_id = gameParticipantId;
        nightActDao.insert(nightAct);

        return 0;
    }
}
