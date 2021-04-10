package com.okaka.onenightjinroh.application.service.result.rules;

import com.okaka.onenightjinroh.application.domain.TallyResult;
import com.okaka.onenightjinroh.application.service.result.WinLoseConditionBase;

import java.util.List;

public class FailPeaceVillage implements WinLoseConditionBase {
    @Override
    public boolean condition(List<TallyResult> tallyResults) {
        // 選ばれた人が2人以下で，選ばれた人は全員村人で，村に村人しかいなかった
        List<TallyResult> selectedPlayers = RuleUtils.getSelectedPlayers(tallyResults);
        if (selectedPlayers.size() > 2) {
            return false;
        }
        if (RuleUtils.containsRole(selectedPlayers, 2L)) {
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
        return "平和村失敗";
    }
}
