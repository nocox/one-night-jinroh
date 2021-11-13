package com.okaka.onenightjinroh.application.domain;

import com.okaka.onenightjinroh.application.bean.GameParticipantBean;

import java.util.List;

public class ParticipantDisplayChecker {
    private final GameParticipant myParticipant;
    private final List<RoleNightActFormatter> roleNightActs;

    public ParticipantDisplayChecker(GameParticipant myParticipant, List<RoleNightActFormatter> roleNightActs) {
        this.myParticipant = myParticipant;
        this.roleNightActs = roleNightActs;
    }

    public static ParticipantDisplayChecker of(GameParticipant myParticipant, List<RoleNightActFormatter> roleNightActs) {
        // 必要があれば，データ整合性の確認を追加する
        return new ParticipantDisplayChecker(myParticipant, roleNightActs);
    }

    public GameParticipantBean check(GameParticipant participant) {

        if (myParticipant.getRole() instanceof Role.Uranaishi) {
            if (roleNightActs.stream().anyMatch(act -> act.showableParticipant(participant.getGameParticipationId()))) {
                return GameParticipantBean.ofRoleOpen(participant);
            }
        }

        if (myParticipant.getGameParticipationId().equals(participant.getGameParticipationId())) {
            return GameParticipantBean.ofRoleOpen(participant);
        }

        return GameParticipantBean.ofRoleHidden(participant);

    }
}
