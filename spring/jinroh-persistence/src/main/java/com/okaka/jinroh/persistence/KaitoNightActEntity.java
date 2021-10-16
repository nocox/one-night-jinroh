package com.okaka.jinroh.persistence;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table(name = "KAITO_NIGHT_ACT")
public class KaitoNightActEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long KAITO_NIGHT_ACT_ID;
    private Long FROM_GAME_PARTICIPATION_ID;
    private Long TO_GAME_PARTICIPATION_ID;

    public KaitoNightActEntity(Long KAITO_NIGHT_ACT_ID, Long FROM_GAME_PARTICIPATION_ID, Long TO_GAME_PARTICIPATION_ID) {
        this.KAITO_NIGHT_ACT_ID = KAITO_NIGHT_ACT_ID;
        this.FROM_GAME_PARTICIPATION_ID = FROM_GAME_PARTICIPATION_ID;
        this.TO_GAME_PARTICIPATION_ID = TO_GAME_PARTICIPATION_ID;
    }

    public KaitoNightActEntity() {}

    public Long getKaitoNightActId() {
        return KAITO_NIGHT_ACT_ID;
    }

    public Long getFromGameParticipationId() {
        return FROM_GAME_PARTICIPATION_ID;
    }

    public Long getToGameParticipationId() {
        return TO_GAME_PARTICIPATION_ID;
    }
}
