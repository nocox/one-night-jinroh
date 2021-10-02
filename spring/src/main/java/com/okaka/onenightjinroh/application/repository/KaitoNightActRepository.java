package com.okaka.onenightjinroh.application.repository;

import com.okaka.jinroh.persistence.KaitoNightActDao;
import com.okaka.jinroh.persistence.KaitoNightActEntity;
import com.okaka.onenightjinroh.application.domain.KaitoNightAct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class KaitoNightActRepository {
    @Autowired
    private KaitoNightActDao dao;

    public void save(KaitoNightAct kaitoNightAct){
        final var entity = new KaitoNightActEntity(
                null,
                kaitoNightAct.getFromGameParticipationId(),
                kaitoNightAct.getToGameParticipationId());
        dao.insert(entity);
    }
}
