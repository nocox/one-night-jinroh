package com.okaka.onenightjinroh.api;

import com.okaka.onenightjinroh.application.service.result.JudgeResultUseCase;
import com.okaka.onenightjinroh.application.service.result.ShowResultTermIndexBean;
import com.okaka.onenightjinroh.application.service.result.WinLoseConditionBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class ShowResultTermController {
    @Autowired
    HttpSession session;

    @Autowired
    JudgeResultUseCase judgeResultUseCase;

    @RequestMapping(path = "/result-index")
    @CrossOrigin(origins = {"http://localhost:8081"}, allowCredentials = "true")
    public ShowResultTermIndexBean getShowResultTermIndex() {
        String strGameId = session.getAttribute("game_id").toString();
        Long gameId = Long.valueOf(strGameId);
        WinLoseConditionBase judge = judgeResultUseCase.judge(gameId);
        return new ShowResultTermIndexBean(judge.getResultText());
    }
}
