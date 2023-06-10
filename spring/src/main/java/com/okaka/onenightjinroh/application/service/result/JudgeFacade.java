package com.okaka.onenightjinroh.application.service.result;

import com.okaka.onenightjinroh.application.domain.KaitoNightActFormatter;
import com.okaka.onenightjinroh.application.domain.TallyResultConsideredNightAct;
import com.okaka.onenightjinroh.application.port.TallyResultPort;
import com.okaka.onenightjinroh.application.service.result.rules.FailPeaceVillage;
import com.okaka.onenightjinroh.application.service.result.rules.SimpleJinrohWin;
import com.okaka.onenightjinroh.application.service.result.rules.SimpleVillageWin;
import com.okaka.onenightjinroh.application.service.result.rules.SuccessHideJinrohWin;
import com.okaka.onenightjinroh.application.service.result.rules.SuccessPeaceVillage;
import com.okaka.onenightjinroh.application.service.result.rules.TuribitoWin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JudgeFacade {
    @Autowired
    TallyResultPort tallyResultPort;

    List<WinLoseConditionBase> winLoseConditions = Arrays.asList(
            new SimpleVillageWin(),
            new SimpleJinrohWin(),
            new SuccessPeaceVillage(),
            new FailPeaceVillage(),
            new SuccessHideJinrohWin(),
            new TuribitoWin());

    public WinLoseConditionBase judge(Long gameId, Optional<KaitoNightActFormatter> kaitoNightActFormatter) {
        List<TallyResultConsideredNightAct> tallyResults = tallyResultPort.searchTallyResults(gameId).stream()
                .map(tallyResult -> new TallyResultConsideredNightAct(tallyResult, kaitoNightActFormatter))
                .collect(Collectors.toList());

        return winLoseConditions.stream()
                .filter(conditionBase -> conditionBase.condition(tallyResults))
                .min(Comparator.comparing(WinLoseConditionBase::priority))
                .orElseThrow();
    }
}
