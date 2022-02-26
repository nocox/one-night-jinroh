package com.okaka.onenightjinroh.application.service.result.rules;

import com.okaka.onenightjinroh.application.domain.TallyResultConsideredNightAct;
import com.okaka.onenightjinroh.application.service.result.WinLoseConditionBase;

import java.util.List;

public class SimpleJinrohWin implements WinLoseConditionBase {
    @Override
    public boolean condition(List<TallyResultConsideredNightAct> tallyResults) {
        // 選ばれた人が2人以下で，選ばれた人は全員村人で，村の中に人狼がいた
        List<TallyResultConsideredNightAct> selectedPlayers = RuleUtils.getSelectedPlayers(tallyResults);
        if (selectedPlayers.size() > 2) {
            return false;
        }
        if (RuleUtils.containsRole(selectedPlayers, 2L)) {
            return false;
        }

        return RuleUtils.containsRole(tallyResults, 2L);
    }

    @Override
    public Integer priority() {
        return 100;
    }

    @Override
    public String getResultText() {
        return "SIMPLE_JINROH_WIN";
    }
}
