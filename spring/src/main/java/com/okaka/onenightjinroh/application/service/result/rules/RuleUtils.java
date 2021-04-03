package com.okaka.onenightjinroh.application.service.result.rules;

import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.domain.TallyResult;

import java.util.List;
import java.util.stream.Collectors;

public class RuleUtils {
    public static boolean containsRole(List<TallyResult> tallyResults, Long selectRoleId) {
            return tallyResults.stream()
                    .map(TallyResult::getGameParticipant)
                    .map(GameParticipant::getRole)
                    .map(Role::getRoleId)
                    .anyMatch(roleId -> roleId.equals(selectRoleId));
    }

    public static List<TallyResult> getSelectedPlayers(List<TallyResult> tallyResults) {
        return tallyResults.stream()
                .filter(TallyResult::getSelected)
                .collect(Collectors.toList());
    }
}
