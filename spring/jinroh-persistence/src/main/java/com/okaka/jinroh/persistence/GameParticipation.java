package com.okaka.jinroh.persistence;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table(name = "GAME_PARTICIPATION")
public class GameParticipation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long game_participation_id;
    public Long game_id;
    public Long user_id;
    public Long role_id;
    public boolean host_flg;
}
