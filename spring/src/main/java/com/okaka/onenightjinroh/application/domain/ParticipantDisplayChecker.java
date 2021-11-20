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
            if (showableParticipant(participant)) {
                return GameParticipantBean.ofRoleOpen(participant);
            }
        } else if (myParticipant.getRole() instanceof Role.Kaito) {
            if (showableParticipant(participant)) {
                return GameParticipantBean.ofChangedKaito(participant);
            }
            if (isMyself(participant)) {
                return GameParticipantBean.ofChangedRole(participant, getKaitoChangedRole());
            }
        } else if (myParticipant.getRole().isJinroh()) {
            if (participant.getRole().isJinroh()) {
                return GameParticipantBean.ofRoleOpen(participant);
            }
        }

        if (isMyself(participant)) {
            return GameParticipantBean.ofRoleOpen(participant);
        }

        return GameParticipantBean.ofRoleHidden(participant);
    }

    private Role getKaitoChangedRole() {
        KaitoNightActFormatter actFormatter = roleNightActs.stream()
                .filter(act -> act instanceof KaitoNightActFormatter)
                .findFirst()
                .map(formatter -> ((KaitoNightActFormatter) formatter))
                .orElseThrow();
        return actFormatter.getToParticipant().getRole();
    }

    private boolean isMyself(GameParticipant participant) {
        return myParticipant.getGameParticipationId().equals(participant.getGameParticipationId());
    }

    private boolean showableParticipant(GameParticipant participant) {
        return roleNightActs.stream().anyMatch(act -> act.showableParticipant(participant.getGameParticipationId()));
    }
}
