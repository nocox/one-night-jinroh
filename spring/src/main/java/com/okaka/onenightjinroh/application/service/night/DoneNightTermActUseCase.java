package com.okaka.onenightjinroh.application.service.night;

import com.okaka.jinroh.persistence.GameParticipationEntity;
import com.okaka.jinroh.persistence.GameParticipationDao;
import com.okaka.jinroh.persistence.NightActEntity;
import com.okaka.jinroh.persistence.NightActDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoneNightTermActUseCase {
    @Autowired
    NightActDao nightActDao;

    @Autowired
    GameParticipationDao gameParticipationDao;


    public void done(Long gameParticipantId){
        NightActEntity nightActEntity = new NightActEntity();
        nightActEntity.game_participation_id = gameParticipantId;
        nightActDao.insert(nightActEntity);


    }

    private boolean allDoneNightAct(Long gameId){
        List<NightActEntity> nightActEntities = nightActDao.selectNightAct(gameId);
        List<GameParticipationEntity> gameParticipants = gameParticipationDao.selectGameParticipantsByGameId(gameId);
        
        return nightActEntities.size() == gameParticipants.size();
    }
}
