package com.okaka.onenightjinroh.api;

import com.okaka.onenightjinroh.application.domain.GameIndexBean;
import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.GameParticipantRepository;
import com.okaka.onenightjinroh.application.service.result.JudgeResultUseCase;
import com.okaka.onenightjinroh.application.service.result.ShowResultTermIndexBean;
import com.okaka.onenightjinroh.application.service.result.WinLoseConditionBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ShowResultTermController {
    @Autowired
    HttpSession session;

    @Autowired
    JudgeResultUseCase judgeResultUseCase;

    @Autowired
    GameParticipantRepository gameParticipantRepository;

    @RequestMapping(path = "/result-index")
    public ShowResultTermIndexBean getShowResultTermIndex() {
        String strGameId = session.getAttribute("game_id").toString();
        Long gameId = Long.valueOf(strGameId);
        String strGameParticipationId = session.getAttribute("game_participation_id").toString();
        Long gameParticipantId = Long.valueOf(strGameParticipationId);

        WinLoseConditionBase judge = judgeResultUseCase.judge(gameId);

        List<GameParticipant> gameParticipants = gameParticipantRepository.findByGameIdWithUserAndRole(gameId);
        GameIndexBean gameIndexBean = new GameIndexBean(gameParticipants, gameParticipantId);

        return new ShowResultTermIndexBean(judge.getResultText(), gameIndexBean);
    }
}
