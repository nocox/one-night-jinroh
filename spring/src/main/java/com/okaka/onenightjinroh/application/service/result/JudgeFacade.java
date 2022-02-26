package com.okaka.onenightjinroh.application.service.result;

import com.okaka.onenightjinroh.application.domain.Judge;
import com.okaka.onenightjinroh.application.domain.KaitoNightActFormatter;
import com.okaka.onenightjinroh.application.domain.TallyResultConsideredNightAct;
import com.okaka.onenightjinroh.application.logic.KaitoNightActExecuteLogic;
import com.okaka.onenightjinroh.application.port.TallyResultPort;
import com.okaka.onenightjinroh.application.repository.KaitoNightActRepository;
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
import java.util.stream.Collectors;

@Service
public class JudgeFacade {
    @Autowired
    TallyResultPort tallyResultPort;
    @Autowired
    KaitoNightActRepository kaitoNightActRepository;
    @Autowired
    KaitoNightActExecuteLogic kaitoNightActExecuteLogic;

    List<WinLoseConditionBase> winLoseConditions = Arrays.asList(
            new SimpleVillageWin(),
            new SimpleJinrohWin(),
            new SuccessPeaceVillage(),
            new FailPeaceVillage(),
            new SuccessHideJinrohWin()
    );

    public Judge judge(Long gameId) {
        KaitoNightActFormatter kaitoNightActFormatter = kaitoNightActRepository.findByGameId(gameId)
                .map(kaitoNightAct -> kaitoNightActExecuteLogic.invoke(kaitoNightAct))
                .orElse(null);

        List<TallyResultConsideredNightAct> tallyResults = tallyResultPort.searchTallyResults(gameId).stream()
                .map(tallyResult -> new TallyResultConsideredNightAct(tallyResult, kaitoNightActFormatter))
                .collect(Collectors.toList());

        WinLoseConditionBase winLoseConditionBase = winLoseConditions.stream()
                .filter(conditionBase -> conditionBase.condition(tallyResults))
                .min(Comparator.comparing(WinLoseConditionBase::priority))
                .orElseThrow();
        return new Judge(winLoseConditionBase.getResultText());
    }
}
