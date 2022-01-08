package com.okaka.jinroh.persistence;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "CO_EVENT")
public class CoEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long co_event_id;
    private Long game_id;
    private LocalDateTime co_tm;

    public CoEventEntity() {
    }

    public CoEventEntity(Long coEventId, Long gameId, LocalDateTime coTm) {
        this.co_event_id = coEventId;
        this.game_id = gameId;
        this.co_tm = coTm;
    }

    public Long getCoEventId() {
        return co_event_id;
    }

    public Long getGameId() {
        return game_id;
    }

    public LocalDateTime getCoTm() {
        return co_tm;
    }
}
