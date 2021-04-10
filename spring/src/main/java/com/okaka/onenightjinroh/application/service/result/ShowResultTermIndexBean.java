package com.okaka.onenightjinroh.application.service.result;

import com.okaka.onenightjinroh.application.domain.GameIndexBean;

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
}
