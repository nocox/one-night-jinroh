package com.okaka.onenightjinroh.application.repository;

import com.okaka.jinroh.persistence.UranaishiNightActDao;
import com.okaka.jinroh.persistence.UranaishiNightActEntity;
import com.okaka.onenightjinroh.application.domain.UranaishiNightAct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UranaishiNightActRepository {
    @Autowired
    UranaishiNightActDao dao;

    public void save(UranaishiNightAct uranaishiNightAct) {
        UranaishiNightActEntity uranaishiNightActEntity = new UranaishiNightActEntity(
                uranaishiNightAct.getUranaishiNightActId(),
                uranaishiNightAct.getFromGameParticipationId(),
                uranaishiNightAct.getToGameParticipationId(),
                uranaishiNightAct.isSelectedHolidayRoles()
        );
        dao.insert(uranaishiNightActEntity);
    }

    public Optional<UranaishiNightAct> findByFromParticipationId(Long participationId) {
        return dao.selectByFromParticipationId(participationId)
                .map(act -> new UranaishiNightAct(
                act.getUranaishiNightActId(),
                act.getFromGameParticipationId(),
                act.getToGameParticipationId(),
                act.isSelectedHolidayRoles())
        );
    }
}
