package com.okaka.onenightjinroh.application.service.result;

import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.domain.TallyResultConsideredNightAct;

import java.util.List;

public interface WinLoseConditionBase {
    boolean condition(List<TallyResultConsideredNightAct> tallyResults);

    Integer priority();

    String getResultText();

    WinOrLose judge(Role role);
}
