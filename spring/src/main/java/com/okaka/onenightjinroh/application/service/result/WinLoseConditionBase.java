package com.okaka.onenightjinroh.application.service.result;

import com.okaka.onenightjinroh.application.domain.TallyResult;

import java.util.List;

public interface WinLoseConditionBase {
    boolean condition(List<TallyResult> tallyResults);
    Integer priority();
    String getResultText();
}
