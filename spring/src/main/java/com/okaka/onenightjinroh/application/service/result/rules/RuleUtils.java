package com.okaka.onenightjinroh.application.service.result.rules;

import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.domain.TallyResultConsideredNightAct;

import java.util.List;
import java.util.stream.Collectors;

public class RuleUtils {
    public static boolean containsRole(List<TallyResultConsideredNightAct> tallyResults, Long selectRoleId) {
            return tallyResults.stream()
                    .map(TallyResultConsideredNightAct::getRole)
                    .map(Role::getRoleId)
                    .anyMatch(roleId -> roleId.equals(selectRoleId));
    }

    public static List<TallyResultConsideredNightAct> getSelectedPlayers(List<TallyResultConsideredNightAct> tallyResults) {
        return tallyResults.stream()
                .filter(TallyResultConsideredNightAct::getSelected)
                .collect(Collectors.toList());
    }
}
