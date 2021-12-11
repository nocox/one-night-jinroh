package com.okaka.jinroh.persistence;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table(name = "CO_EVENT")
public class CoEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coEventId;
    private Long gameId;
    private Long coTm;

    public CoEventEntity() {
    }

    public CoEventEntity(Long coEventId, Long gameId, Long coTm) {
        this.coEventId = coEventId;
        this.gameId = gameId;
        this.coTm = coTm;
    }

    public Long getCoEventId() {
        return coEventId;
    }

    public Long getGameId() {
        return gameId;
    }

    public Long getCoTm() {
        return coTm;
    }
}
