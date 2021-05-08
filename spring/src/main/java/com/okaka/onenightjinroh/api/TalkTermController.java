package com.okaka.onenightjinroh.api;

import com.okaka.onenightjinroh.application.service.talk.GetTalkTermIndexUseCase;
import com.okaka.onenightjinroh.application.service.talk.TalkTermIndexBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class TalkTermController {
    @Autowired
    GetTalkTermIndexUseCase getTalkTermIndexUseCase;

    @Autowired
    HttpSession session;

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @RequestMapping(path = "/talk-index")
    public TalkTermIndexBean getTalkTermIndex() {
        String strGameId = session.getAttribute("game_id").toString();
        Long gameId = Long.valueOf(strGameId);

        String strGameParticipationId = session.getAttribute("game_participation_id").toString();
        Long gameParticipantId = Long.valueOf(strGameParticipationId);

        return getTalkTermIndexUseCase.get(gameId, gameParticipantId);
    }

    @RequestMapping(path = "/end-talk")
    public int endTalk() {
        String strGameId = session.getAttribute("game_id").toString();
        Long gameId = Long.valueOf(strGameId);
        messagingTemplate.convertAndSend("/topic/end-talk/" + gameId, "");
        return 0;
    }
}
