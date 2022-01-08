package com.okaka.jinroh.persistence;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Table;

@Entity
@Table(name = "CO_STATE")
public class CoStateEntity {

    @Column(name = "co_event_id")
    private Long co_event_id;

    @Column(name = "game_participation_id")
    private Long game_participation_id;

    @Column(name = "role_id")
    private Long role_id;

    public CoStateEntity() {
    }

    public CoStateEntity(Long coEventId, Long gameParticipationId, Long roleId) {
        this.co_event_id = coEventId;
        this.game_participation_id = gameParticipationId;
        this.role_id = roleId;
    }

    public Long getCoEventId() {
        return co_event_id;
    }

    public Long getGameParticipationId() {
        return game_participation_id;
    }

    public Long getRoleId() {
        return role_id;
    }
}
