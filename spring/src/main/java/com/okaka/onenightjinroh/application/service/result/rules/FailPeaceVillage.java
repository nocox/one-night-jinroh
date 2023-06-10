package com.okaka.onenightjinroh.application.service.result.rules;

import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.domain.TallyResultConsideredNightAct;
import com.okaka.onenightjinroh.application.service.result.WinLoseConditionBase;
import com.okaka.onenightjinroh.application.service.result.WinOrLose;

import java.util.List;

public class FailPeaceVillage implements WinLoseConditionBase {
    @Override
    public boolean condition(List<TallyResultConsideredNightAct> tallyResults) {
        // 選ばれた人は全員村人で，村に村人しかいなかった
        boolean selectPeaceVillage = tallyResults.stream().allMatch(it -> it.getTallyResult().getVoteCount() == 1);
        boolean existsJinroh = RuleUtils.containsRole(tallyResults, 2L);
        return !selectPeaceVillage && !existsJinroh;
    }

    @Override
    public Integer priority() {
        return 100;
    }

    @Override
    public String getResultText() {
        return "FAIL_PEACE_VILLAGE";
    }

    @Override
    public WinOrLose judge(Role role) {
        return WinOrLose.lose;
    }
}
