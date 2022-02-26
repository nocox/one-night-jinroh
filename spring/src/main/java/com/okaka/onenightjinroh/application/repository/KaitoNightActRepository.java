package com.okaka.onenightjinroh.application.repository;

import com.okaka.jinroh.persistence.KaitoNightActDao;
import com.okaka.jinroh.persistence.KaitoNightActEntity;
import com.okaka.onenightjinroh.application.domain.KaitoNightAct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class KaitoNightActRepository {
    @Autowired
    private KaitoNightActDao dao;

    public Optional<KaitoNightAct> findByParticipationId(Long participationId) {
        return dao.selectByFromParticipationId(participationId)
                .map(act -> KaitoNightAct.of(
                        act.getFromGameParticipationId(),
                        act.getToGameParticipationId()
                ));
    }

    public Optional<KaitoNightAct> findByGameId(Long gameId) {
        return dao.selectByGameId(gameId)
                .map(act -> KaitoNightAct.of(
                        act.getFromGameParticipationId(),
                        act.getToGameParticipationId()
                ));
    }

    public void save(KaitoNightAct kaitoNightAct){
        final var entity = new KaitoNightActEntity(
                null,
                kaitoNightAct.getFromGameParticipationId(),
                kaitoNightAct.getToGameParticipationId());
        dao.insert(entity);
    }
}
