package com.okaka.onenightjinroh.application.service.result;

import com.okaka.onenightjinroh.application.domain.TallyResult;
import com.okaka.onenightjinroh.application.port.TallyResultPort;
import com.okaka.onenightjinroh.application.service.result.rules.SimpleJinrohWin;
import com.okaka.onenightjinroh.application.service.result.rules.SimpleVillageWin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class JudgeResultUseCase {
    @Autowired
    TallyResultPort tallyResultPort;

    List<WinLoseConditionBase> winLoseConditions = Arrays.asList(
            new SimpleVillageWin(),
            new SimpleJinrohWin()
    );

    public WinLoseConditionBase judge(Long gameId) {
        List<TallyResult> tallyResults = tallyResultPort.searchTallyResults(gameId);
        return winLoseConditions.stream()
                .filter(conditionBase -> conditionBase.condition(tallyResults))
                .min(Comparator.comparing(WinLoseConditionBase::priority))
                .orElseThrow();
    }
}
