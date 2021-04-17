package com.okaka.onenightjinroh.api;

import com.okaka.onenightjinroh.application.service.tally.GetTallyTermIndexUseCase;
import com.okaka.onenightjinroh.application.service.tally.TallyTermIndexBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class TallyTermController {
    @Autowired
    HttpSession session;

    @Autowired
    GetTallyTermIndexUseCase getTallyTermIndexUseCase;

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @RequestMapping(path = "/tally-index")
    @CrossOrigin(origins = {"http://ec2-52-198-98-214.ap-northeast-1.compute.amazonaws.com"}, allowCredentials = "true")
    public TallyTermIndexBean getTallyTermIndex() {
        String strGameId = session.getAttribute("game_id").toString();
        Long gameId = Long.valueOf(strGameId);
        String strGameParticipationId = session.getAttribute("game_participation_id").toString();
        Long gameParticipantId = Long.valueOf(strGameParticipationId);

        return getTallyTermIndexUseCase.get(gameId, gameParticipantId);
    }

    @RequestMapping(path = "/show-result")
    @CrossOrigin(origins = {"http://ec2-52-198-98-214.ap-northeast-1.compute.amazonaws.com"}, allowCredentials = "true")
    public int showResult() {
        String strGameId = session.getAttribute("game_id").toString();
        Long gameId = Long.valueOf(strGameId);
        messagingTemplate.convertAndSend("/topic/result/" + gameId, "");
        return 0;
    }

}
