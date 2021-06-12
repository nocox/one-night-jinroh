package com.okaka.onenightjinroh.application.service.result;

import com.okaka.onenightjinroh.application.domain.Judge;
import com.okaka.onenightjinroh.application.domain.TallyResult;
import com.okaka.onenightjinroh.application.port.TallyResultPort;
import com.okaka.onenightjinroh.application.service.result.rules.FailPeaceVillage;
import com.okaka.onenightjinroh.application.service.result.rules.SimpleJinrohWin;
import com.okaka.onenightjinroh.application.service.result.rules.SimpleVillageWin;
import com.okaka.onenightjinroh.application.service.result.rules.SuccessHideJinrohWin;
import com.okaka.onenightjinroh.application.service.result.rules.SuccessPeaceVillage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class JudgeFacade {
    @Autowired
    TallyResultPort tallyResultPort;

    List<WinLoseConditionBase> winLoseConditions = Arrays.asList(
            new SimpleVillageWin(),
            new SimpleJinrohWin(),
            new SuccessPeaceVillage(),
            new FailPeaceVillage(),
            new SuccessHideJinrohWin()
    );

    public Judge judge(Long gameId) {
        List<TallyResult> tallyResults = tallyResultPort.searchTallyResults(gameId);
        WinLoseConditionBase winLoseConditionBase = winLoseConditions.stream()
                .filter(conditionBase -> conditionBase.condition(tallyResults))
                .min(Comparator.comparing(WinLoseConditionBase::priority))
                .orElseThrow();
        return new Judge(winLoseConditionBase.getResultText());
    }
}
