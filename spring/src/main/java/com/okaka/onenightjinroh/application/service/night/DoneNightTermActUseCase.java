package com.okaka.onenightjinroh.application.service.night;

import com.okaka.jinroh.persistence.GameParticipationDao;
import com.okaka.jinroh.persistence.NightActEntity;
import com.okaka.jinroh.persistence.NightActDao;
import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.GameParticipantRepository;
import com.okaka.onenightjinroh.application.domain.NightAct;
import com.okaka.onenightjinroh.application.domain.NightActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoneNightTermActUseCase {
    @Autowired
    NightActDao nightActDao;

    @Autowired
    GameParticipationDao gameParticipationDao;

    @Autowired
    GameParticipantRepository gameParticipantRepository;

    @Autowired
    NightActRepository nightActRepository;


    public void done(Long gameParticipantId, Long gameId){
        NightActEntity nightActEntity = new NightActEntity();
        nightActEntity.game_participation_id = gameParticipantId;
        nightActDao.insert(nightActEntity);

        if (allDoneNightAct(gameId)) {
            System.out.println("all done !!!");
        }
    }

    private boolean allDoneNightAct(Long gameId){
        List<GameParticipant> gameParticipants = gameParticipantRepository.findByGameId(gameId);
        List<NightAct> nightActs = nightActRepository.findByGameId(gameId);
        return gameParticipants.stream()
                .map(GameParticipant::getGameParticipationId)
                .allMatch(gameParticipantId -> nightActs.stream()
                        .map(NightAct::getGameParticipant)
                        .map(GameParticipant::getGameParticipationId)
                        .collect(Collectors.toList())
                        .contains(gameParticipantId));
    }
}
