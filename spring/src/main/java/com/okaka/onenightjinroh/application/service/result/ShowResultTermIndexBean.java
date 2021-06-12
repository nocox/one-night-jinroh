package com.okaka.onenightjinroh.application.service.result;

import com.okaka.onenightjinroh.application.bean.GameIndexBean;
import com.okaka.onenightjinroh.application.domain.GameResult;

public class ShowResultTermIndexBean {
    public String judgeText;
    public GameIndexBean gameIndex;

    public ShowResultTermIndexBean(String judgeText, GameIndexBean gameIndex) {
        this.judgeText = judgeText;
        this.gameIndex = gameIndex;
    }

    public GameIndexBean getGameIndex() {
        return gameIndex;
    }

    public String getJudgeText() {
        return judgeText;
    }

    public static ShowResultTermIndexBean fromDomain(GameResult gameResult){
        GameIndexBean gameIndexBean = new GameIndexBean(gameResult.getGameParticipants(), gameResult.getGameParticipantId());
        return new ShowResultTermIndexBean(gameResult.getJudge().getResultText(), gameIndexBean);
    }
}
