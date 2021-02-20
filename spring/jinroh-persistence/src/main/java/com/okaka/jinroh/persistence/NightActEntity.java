package com.okaka.jinroh.persistence;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table(name = "NIGHT_ACT")
public class NightActEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long night_act_id;
    public Long game_participation_id;
}
