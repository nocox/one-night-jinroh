package com.okaka.onenightjinroh.application.service.talk;

import com.okaka.jinroh.persistence.CoEventDao;
import com.okaka.jinroh.persistence.CoEventEntity;
import com.okaka.jinroh.persistence.CoStateDao;
import com.okaka.jinroh.persistence.CoStateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CoRepository {

    @Autowired
    CoEventDao coEventDao;

    @Autowired
    CoStateDao coStateDao;

    public Optional<Long> findCoEventId(Long gameId) {
        return coEventDao.selectByGameId(gameId);
    }

    public List<CoDto> findCoState(Long coEventId) {
        return coStateDao.selectByCoEventId(coEventId).stream().map(entity ->
             new CoDto(entity.getGameParticipationId(), entity.getRoleId())
        ).collect(Collectors.toList());
    }

    public Long saveCoEvent(Long gameId, LocalDateTime coTm) {
        CoEventEntity coEventEntity = new CoEventEntity(null, gameId, coTm);
        coEventDao.insert(coEventEntity);
        return coEventEntity.getCoEventId();
    }

    public void saveCoState(Long coEventId, CoState coState) {
        coState.getCos().forEach(co -> {
            CoStateEntity coStateEntity = new CoStateEntity(
                    coEventId,
                    co.getId(),
                    co.getRole().getRoleId());
            coStateDao.insert(coStateEntity);
        });
    }

    public static class CoDto {
        private Long gameParticipationId;

        private Long roleId;

        public CoDto(Long gameParticipationId, Long roleId) {
            this.gameParticipationId = gameParticipationId;
            this.roleId = roleId;
        }

        public Long getGameParticipationId() {
            return gameParticipationId;
        }

        public Long getRoleId() {
            return roleId;
        }
    }
}
