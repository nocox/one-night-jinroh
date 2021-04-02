package com.okaka.onenightjinroh.application.service.result.rules;

import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.domain.TallyResult;
import com.okaka.onenightjinroh.application.service.result.WinLoseConditionBase;

import java.util.List;
import java.util.stream.Collectors;

public class SimpleJinrohWin implements WinLoseConditionBase {
    @Override
    public boolean condition(List<TallyResult> tallyResults) {
        // 選ばれた人が2人以下で，選ばれた人は全員村人で，村の中に人狼がいた
        List<TallyResult> selectedPlayers = tallyResults.stream()
                .filter(TallyResult::getSelected)
                .collect(Collectors.toList());
        if (selectedPlayers.size() > 2) {
            return false;
        }

        boolean selectedJinroh = selectedPlayers.stream()
                .map(TallyResult::getGameParticipant)
                .map(GameParticipant::getRole)
                .map(Role::getRoleId)
                .anyMatch(roleId -> roleId == 2); // 2: 人狼
        if (selectedJinroh) {
            return false;
        }

        boolean existsJinroh = tallyResults.stream()
                .map(TallyResult::getGameParticipant)
                .map(GameParticipant::getRole)
                .map(Role::getRoleId)
                .anyMatch(roleId -> roleId == 2);
        return existsJinroh;
    }

    @Override
    public Integer priority() {
        return 100;
    }

    @Override
    public String getResultText() {
        return "人狼側の勝利";
    }
}
