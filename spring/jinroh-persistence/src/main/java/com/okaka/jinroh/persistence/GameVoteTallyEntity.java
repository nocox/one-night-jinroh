package com.okaka.jinroh.persistence;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table(name = "GAME_VOTE_TALLY")
public class GameVoteTallyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long game_vote_tally_id;
    public Long game_id;
    public Long game_participation_id;
    public int vote_count;
    public boolean selected;
}
