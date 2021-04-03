package com.okaka.onenightjinroh.application.service.result.rules;

import com.okaka.onenightjinroh.application.domain.TallyResult;
import com.okaka.onenightjinroh.application.service.result.WinLoseConditionBase;

import java.util.List;

public class SuccessPeaceVillage implements WinLoseConditionBase {
    @Override
    public boolean condition(List<TallyResult> tallyResults) {
        // 選ばれた人が3人以上で，村の中に人狼がいない．
        List<TallyResult> selectedPlayers = RuleUtils.getSelectedPlayers(tallyResults);
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
        return "平和村成功";
    }
}
