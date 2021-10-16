package com.okaka.jinroh.persistence;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table(name = "URNAISHI_NIGHT_ACT")
public class UranaishiNightActEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long URNAISHI_NIGHT_ACT_ID;
    public Long FROM_GAME_PARTICIPATION_ID;
    public Long TO_GAME_PARTICIPATION_ID;
    public boolean SELECTED_HOLIDAY_ROLES;

    public UranaishiNightActEntity() {
    }

    public UranaishiNightActEntity(Long URNAISHI_NIGHT_ACT_ID, Long FROM_GAME_PARTICIPATION_ID, Long TO_GAME_PARTICIPATION_ID, boolean SELECTED_HOLIDAY_ROLES) {
        this.URNAISHI_NIGHT_ACT_ID = URNAISHI_NIGHT_ACT_ID;
        this.FROM_GAME_PARTICIPATION_ID = FROM_GAME_PARTICIPATION_ID;
        this.TO_GAME_PARTICIPATION_ID = TO_GAME_PARTICIPATION_ID;
        this.SELECTED_HOLIDAY_ROLES = SELECTED_HOLIDAY_ROLES;
    }

    public Long getUranaishiNightActId() {
        return URNAISHI_NIGHT_ACT_ID;
    }

    public Long getFromGameParticipationId() {
        return FROM_GAME_PARTICIPATION_ID;
    }

    public Long getToGameParticipationId() {
        return TO_GAME_PARTICIPATION_ID;
    }

    public boolean isSelectedHolidayRoles() {
        return SELECTED_HOLIDAY_ROLES;
    }
}
