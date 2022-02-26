package com.okaka.onenightjinroh.application.service.result.rules;

import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.domain.TallyResultConsideredNightAct;
import com.okaka.onenightjinroh.application.service.result.WinLoseConditionBase;
import com.okaka.onenightjinroh.application.service.result.WinOrLose;

import java.util.List;

public class SimpleVillageWin implements WinLoseConditionBase {
    @Override
    public boolean condition(List<TallyResultConsideredNightAct> tallyResults) {
        // 選ばれた人が2人以下で，その中に人狼がいた
        List<TallyResultConsideredNightAct> selectedPlayers = RuleUtils.getSelectedPlayers(tallyResults);
        if (selectedPlayers.size() > 2) {
            return false;
        }
        return RuleUtils.containsRole(selectedPlayers, 2L);
    }

    @Override
    public Integer priority() {
        return 100;
    }

    @Override
    public String getResultText() {
        return "SIMPLE_VILLAGE_WIN";
    }

    @Override
    public WinOrLose judge(Role role) {
        if (role.getRoleId() == 2 || role.getRoleId() == 5 || role.getRoleId() == 6) {
            return WinOrLose.lose;
        } else {
            return WinOrLose.win;
        }
    }
}
