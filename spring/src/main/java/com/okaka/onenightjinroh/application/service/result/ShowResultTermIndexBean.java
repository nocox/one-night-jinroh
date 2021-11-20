package com.okaka.onenightjinroh.application.service.result;

import com.okaka.onenightjinroh.application.bean.GameIndexBean;
import com.okaka.onenightjinroh.application.domain.GameResult;
import com.okaka.onenightjinroh.application.service.room.RoleBean;

import java.util.List;
import java.util.stream.Collectors;

public class ShowResultTermIndexBean {
    private String judgeText;
    private GameIndexBean gameIndex;
    private List<RoleBean> holidayRoles;

    public ShowResultTermIndexBean(String judgeText, GameIndexBean gameIndex, List<RoleBean> holidayRoles) {
        this.judgeText = judgeText;
        this.gameIndex = gameIndex;
        this.holidayRoles = holidayRoles;
    }

    public GameIndexBean getGameIndex() {
        return gameIndex;
    }

    public String getJudgeText() {
        return judgeText;
    }

    public List<RoleBean> getHolidayRoles() {
        return holidayRoles;
    }

    public static ShowResultTermIndexBean fromDomain(GameResult gameResult){
        GameIndexBean gameIndexBean = GameIndexBean.ofOpenRole(gameResult.getGameParticipants(), gameResult.getGameParticipantId());
        List<RoleBean> holidayRoles = gameResult.getHolidayRoles().getRoles().stream().map(RoleBean::new).collect(Collectors.toList());
        return new ShowResultTermIndexBean(gameResult.getJudge().getResultText(), gameIndexBean, holidayRoles);
    }
}
