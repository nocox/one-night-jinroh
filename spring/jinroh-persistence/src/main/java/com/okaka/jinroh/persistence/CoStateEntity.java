package com.okaka.jinroh.persistence;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Table;

@Entity
@Table(name = "CO_STATE")
public class CoStateEntity {

    @Column(name = "co_event_id")
    private Long coEventId;

    @Column(name = "game_participation_id")
    private Long gameParticipationId;

    @Column(name = "role_id")
    private Long roleId;

    public CoStateEntity() {
    }

    public CoStateEntity(Long coEventId, Long gameParticipationId, Long roleId) {
        this.coEventId = coEventId;
        this.gameParticipationId = gameParticipationId;
        this.roleId = roleId;
    }

    public Long getCoEventId() {
        return coEventId;
    }

    public Long getGameParticipationId() {
        return gameParticipationId;
    }

    public Long getRoleId() {
        return roleId;
    }
}
