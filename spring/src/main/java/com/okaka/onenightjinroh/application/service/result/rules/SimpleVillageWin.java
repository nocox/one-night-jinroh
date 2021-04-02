package com.okaka.onenightjinroh.application.service.result.rules;

import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.domain.TallyResult;
import com.okaka.onenightjinroh.application.service.result.WinLoseConditionBase;

import java.util.List;
import java.util.stream.Collectors;

public class SimpleVillageWin implements WinLoseConditionBase {
    @Override
    public boolean condition(List<TallyResult> tallyResults) {
        // 選ばれた人が2人以下で，その中に人狼がいた
        List<TallyResult> selectedPlayers = tallyResults.stream()
                .filter(TallyResult::getSelected)
                .collect(Collectors.toList());
        if (selectedPlayers.size() > 2) {
            return false;
        }
        return selectedPlayers.stream()
                .map(TallyResult::getGameParticipant)
                .map(GameParticipant::getRole)
                .map(Role::getRoleId)
                .anyMatch(roleId -> roleId == 2); // 2: 人狼
    }

    @Override
    public Integer priority() {
        return 100;
    }

    @Override
    public String getResultText() {
        return "村人側の勝利";
    }
}
