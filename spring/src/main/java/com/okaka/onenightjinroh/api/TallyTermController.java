package com.okaka.onenightjinroh.api;

import com.okaka.onenightjinroh.application.service.tally.GetTallyTermIndexUseCase;
import com.okaka.onenightjinroh.application.service.tally.ShowResultUseCase;
import com.okaka.onenightjinroh.application.service.tally.TallyTermIndexBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    ShowResultUseCase showResultUseCase;

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @RequestMapping(path = "/tally-index")
    public TallyTermIndexBean getTallyTermIndex() {
        String strGameId = session.getAttribute("game_id").toString();
        Long gameId = Long.valueOf(strGameId);
        String strGameParticipationId = session.getAttribute("game_participation_id").toString();
        Long gameParticipantId = Long.valueOf(strGameParticipationId);

        return getTallyTermIndexUseCase.get(gameId, gameParticipantId);
    }

    @RequestMapping(path = "/show-result")
    public int showResult() {
        String strGameId = session.getAttribute("game_id").toString();
        Long gameId = Long.valueOf(strGameId);
        showResultUseCase.invoke(gameId);
        messagingTemplate.convertAndSend("/topic/result/" + gameId, "");
        return 0;
    }

}
