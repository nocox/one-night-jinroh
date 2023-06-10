package com.okaka.onenightjinroh.application.service.result.rules;

import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.domain.TallyResultConsideredNightAct;
import com.okaka.onenightjinroh.application.service.result.WinLoseConditionBase;
import com.okaka.onenightjinroh.application.service.result.WinOrLose;

import java.util.List;

public class SuccessPeaceVillage implements WinLoseConditionBase {
    @Override
    public boolean condition(List<TallyResultConsideredNightAct> tallyResults) {
        // 全員の投票数が1で、村に人狼がいない
        boolean selectPeaceVillage = tallyResults.stream().allMatch(it -> it.getTallyResult().getVoteCount() == 1);
        boolean existsJinroh = RuleUtils.containsRole(tallyResults, 2L);
        return selectPeaceVillage && !existsJinroh;
    }

    @Override
    public Integer priority() {
        return 25;
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
