package com.okaka.onenightjinroh.api;

import com.okaka.onenightjinroh.application.service.night.DoneNightTermActUseCase;
import com.okaka.onenightjinroh.application.service.night.GamePersonalBean;
import com.okaka.onenightjinroh.application.service.night.GetGamePersonalUseCase;
import com.okaka.onenightjinroh.application.service.night.GetNightTermIndexUseCase;
import com.okaka.onenightjinroh.application.service.night.NightTermIndexBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class NightController {
    @Autowired
    HttpSession session;

    @Autowired
    GetNightTermIndexUseCase getNightTermIndexUseCase;

    @Autowired
    DoneNightTermActUseCase doneNightTermActUseCase;

    @Autowired
    GetGamePersonalUseCase getGamePersonalUseCase;

    @RequestMapping(path = "/night-index")
    NightTermIndexBean getNightTermIndex() {
        String uuid = session.getAttribute("room_uuid").toString();
        String strUserId = session.getAttribute("user_id").toString();
        Long userId = Long.valueOf(strUserId);

        GamePersonalBean gamePersonalBean = getGamePersonalUseCase.get(uuid, userId);
        session.setAttribute("game_id", gamePersonalBean.getGameId());
        session.setAttribute("game_participation_id", gamePersonalBean.getGameParticipationId());

        return getNightTermIndexUseCase.get(gamePersonalBean.getGameId(), gamePersonalBean.getGameParticipationId());
    }

    @RequestMapping(path = "/done-night-act")
    int doneNightTermAct() {
        String strGameId = session.getAttribute("game_id").toString();
        Long gameId = Long.valueOf(strGameId);

        String strGameParticipationId = session.getAttribute("game_participation_id").toString();
        Long gameParticipantId = Long.valueOf(strGameParticipationId);
        doneNightTermActUseCase.done(gameParticipantId, gameId);

        return 0;
    }
}
