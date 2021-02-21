package com.okaka.onenightjinroh.api;

import com.okaka.onenightjinroh.application.service.talk.GetTalkTermIndexUseCase;
import com.okaka.onenightjinroh.application.service.talk.TalkTermIndexBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class TalkTermController {
    @Autowired
    GetTalkTermIndexUseCase getTalkTermIndexUseCase;

    @Autowired
    HttpSession session;

    @RequestMapping(path = "/talk-index")
    @CrossOrigin(origins = {"http://localhost:8081"}, allowCredentials = "true")
    public TalkTermIndexBean getTalkTermIndex() {
        String strGameId = session.getAttribute("game_id").toString();
        Long gameId = Long.valueOf(strGameId);

        String strGameParticipationId = session.getAttribute("game_participation_id").toString();
        Long gameParticipantId = Long.valueOf(strGameParticipationId);

        return getTalkTermIndexUseCase.get(gameId,gameParticipantId);
    }
}
