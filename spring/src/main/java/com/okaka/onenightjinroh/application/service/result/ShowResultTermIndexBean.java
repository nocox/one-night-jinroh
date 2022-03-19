package com.okaka.onenightjinroh.application.service.result;

import com.okaka.onenightjinroh.application.domain.GameResult;
import com.okaka.onenightjinroh.application.domain.Role;

import java.util.List;
import java.util.stream.Collectors;

public class ShowResultTermIndexBean {
    private String judge;
    private List<GameParticipantWithResultBean> participants;
    private List<String> holidayRoles;

    public ShowResultTermIndexBean(String judge, List<GameParticipantWithResultBean> participants, List<String> holidayRoles) {
        this.judge = judge;
        this.participants = participants;
        this.holidayRoles = holidayRoles;
    }

    public List<GameParticipantWithResultBean> getParticipants() {
        return participants;
    }

    public String getJudge() {
        return judge;
    }

    public List<String> getHolidayRoles() {
        return holidayRoles;
    }

    public static ShowResultTermIndexBean fromDomain(GameResult gameResult) {
        List<GameParticipantWithResultBean> participants = gameResult.getGameParticipants().stream()
                .map(participant -> new GameParticipantWithResultBean(
                            participant.getPlayerName(),
                            participant.getRoleEngStrConsideredNightAct(), // TODO: 快盗の交換後の役職を表示している．コメントの実装時に，元の役職をここで表示し，コメントに快盗と交換したことを表示する．
                            participant.getCoRoleEngStr(),
                            participant.getWinOrLose(),
                            participant.myself(gameResult.getGameParticipantId()),
                            participant.getComment()
                    )
                ).collect(Collectors.toList());
        List<String> holidayRoles = gameResult.getHolidayRoles().getRoles().stream()
                .map(Role::getRoleEngName)
                .collect(Collectors.toList());
        return new ShowResultTermIndexBean(gameResult.getJudge().getResultText(), participants, holidayRoles);
    }
}
