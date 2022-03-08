package com.okaka.onenightjinroh.application.repository;

import com.okaka.jinroh.persistence.KaitoNightActDao;
import com.okaka.jinroh.persistence.KaitoNightActEntity;
import com.okaka.onenightjinroh.application.domain.KaitoNightActFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class KaitoNightActRepository {
    @Autowired
    private KaitoNightActDao dao;
    @Autowired
    private GameParticipantRepository gameParticipantRepository;

    public Optional<KaitoNightActFormatter> findByParticipationId(Long participationId) {
        return dao.selectByFromParticipationId(participationId)
                .map(act -> toDomain(
                        act.getFromGameParticipationId(),
                        act.getToGameParticipationId())
                );
    }

    public Optional<KaitoNightActFormatter> findByGameId(Long gameId) {
        return dao.selectByGameId(gameId)
                .map(act -> toDomain(
                        act.getFromGameParticipationId(),
                        act.getToGameParticipationId())
                );
    }

    // kaitouNightActが直前に取得できているときはこちらを使用する．
    public KaitoNightActFormatter toDomain(final Long fromParticipantId, final Long toParticipantId) {
        final var toParticipant = gameParticipantRepository.findByParticipantId(toParticipantId).orElseThrow();
        final var fromParticipant = gameParticipantRepository.findByParticipantId(fromParticipantId).orElseThrow();
        return KaitoNightActFormatter.of(toParticipant, fromParticipant);
    }

    public void save(final Long fromParticipantId, final Long toParticipantId){
        final var entity = new KaitoNightActEntity(
                null,
                fromParticipantId,
                toParticipantId);
        dao.insert(entity);
    }
}
