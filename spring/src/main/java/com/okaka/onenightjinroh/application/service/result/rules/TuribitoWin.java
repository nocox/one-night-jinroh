package com.okaka.onenightjinroh.application.service.result.rules;

import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.domain.TallyResultConsideredNightAct;
import com.okaka.onenightjinroh.application.service.result.WinLoseConditionBase;
import com.okaka.onenightjinroh.application.service.result.WinOrLose;

import java.util.List;

public class TuribitoWin implements WinLoseConditionBase {

    @Override
    public boolean condition(List<TallyResultConsideredNightAct> tallyResults) {
        // 選ばれた人が2人以下で，そのうちの1人が吊人だったら
        List<TallyResultConsideredNightAct> selectedPlayers = RuleUtils.getSelectedPlayers(tallyResults);
        if (selectedPlayers.size() > 2) {
            return false;
        }
        return RuleUtils.containsRole(selectedPlayers, 6L);
    }

    @Override
    public Integer priority() {
        return 50;
    }

    @Override
    public String getResultText() {
        return "TURIBITO_WIN";
    }

    @Override
    public WinOrLose judge(Role role) {
        if (role.getRoleId() == 6) {
            return WinOrLose.win;
        }
        return WinOrLose.lose;
    }
}
