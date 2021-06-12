package com.okaka.onenightjinroh.api;

import com.okaka.onenightjinroh.application.domain.GameResult;
import com.okaka.onenightjinroh.application.service.result.GetGameResultUseCase;
import com.okaka.onenightjinroh.application.service.result.ShowResultTermIndexBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class ShowResultTermController {
    @Autowired
    HttpSession session;

    @Autowired
    GetGameResultUseCase useCase;

    @RequestMapping(path = "/result-index")
    public ShowResultTermIndexBean getShowResultTermIndex() {
        String strGameId = session.getAttribute("game_id").toString();
        Long gameId = Long.valueOf(strGameId);
        String strGameParticipationId = session.getAttribute("game_participation_id").toString();
        Long gameParticipantId = Long.valueOf(strGameParticipationId);

        GameResult gameResult = useCase.invoke(gameId, gameParticipantId);
        return ShowResultTermIndexBean.fromDomain(gameResult);
    }
}
