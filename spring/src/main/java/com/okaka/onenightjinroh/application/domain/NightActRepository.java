package com.okaka.onenightjinroh.application.domain;

import com.okaka.jinroh.persistence.GameParticipationDao;
import com.okaka.jinroh.persistence.GameParticipationEntity;
import com.okaka.jinroh.persistence.NightActDao;
import com.okaka.jinroh.persistence.NightActEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class NightActRepository {
    @Autowired
    NightActDao nightActDao;

    @Autowired
    GameParticipationDao gameParticipationDao;

    public List<NightAct> findByGameId(Long gameId){
        List<NightActEntity> nightActEntities = nightActDao.selectNightAct(gameId);

        return nightActEntities.stream()
                .map(NightActRepository::toDomain)
                .collect(Collectors.toList());
    }

    private static NightAct toDomain(NightActEntity entity) {
        NightAct nightAct = new NightAct();
        nightAct.setNightActId(entity.night_act_id);
        nightAct.setGameParticipant(new GameParticipant(entity.game_participation_id));
        return nightAct;
    }

}
