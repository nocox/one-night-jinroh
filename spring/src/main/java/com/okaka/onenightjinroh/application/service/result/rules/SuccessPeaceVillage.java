package com.okaka.onenightjinroh.application.service.result.rules;

import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.domain.TallyResultConsideredNightAct;
import com.okaka.onenightjinroh.application.service.result.WinLoseConditionBase;
import com.okaka.onenightjinroh.application.service.result.WinOrLose;

import java.util.List;

public class SuccessPeaceVillage implements WinLoseConditionBase {
    @Override
    public boolean condition(List<TallyResultConsideredNightAct> tallyResults) {
        // 選ばれた人が3人以上で，村の中に人狼がいない．
        List<TallyResultConsideredNightAct> selectedPlayers = RuleUtils.getSelectedPlayers(tallyResults);
        if (selectedPlayers.size() < 3) {
            return false;
        }
        boolean existsJinroh = RuleUtils.containsRole(tallyResults, 2L);
        return existsJinroh == false;
    }

    @Override
    public Integer priority() {
        return 100;
    }

    @Override
    public String getResultText() {
        return "SUCCESS_PEACE_VILLAGE";
    }

    @Override
    public WinOrLose judge(Role role) {
        if (role.getRoleId() == 6) {
            return WinOrLose.lose;
        } else {
            return WinOrLose.win;
        }
    }
}
