package com.okaka.onenightjinroh.application.service.result.rules;

import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.domain.TallyResultConsideredNightAct;
import com.okaka.onenightjinroh.application.service.result.WinLoseConditionBase;
import com.okaka.onenightjinroh.application.service.result.WinOrLose;

import java.util.List;

public class SuccessHideJinrohWin implements WinLoseConditionBase {
    @Override
    public boolean condition(List<TallyResultConsideredNightAct> tallyResults) {
        // 平和村を選んだが，村の中に人狼がいる．
        boolean selectPeaceVillage = tallyResults.stream().allMatch(it -> it.getTallyResult().getVoteCount() == 1);
        boolean existsJinroh = RuleUtils.containsRole(tallyResults, 2L);
        return selectPeaceVillage && existsJinroh;
    }

    @Override
    public Integer priority() {
        return 25;
    }

    @Override
    public String getResultText() {
        return "SUCCESS_HIDE_JINROH_WIN";
    }

    @Override
    public WinOrLose judge(Role role) {
        if (role.getRoleId() == 2 || role.getRoleId() == 5) {
            return WinOrLose.win;
        } else {
            return WinOrLose.lose;
        }
    }
}
