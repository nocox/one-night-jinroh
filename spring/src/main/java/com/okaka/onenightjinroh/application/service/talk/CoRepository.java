package com.okaka.onenightjinroh.application.service.talk;

import com.okaka.jinroh.persistence.CoEventDao;
import com.okaka.jinroh.persistence.CoStateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
