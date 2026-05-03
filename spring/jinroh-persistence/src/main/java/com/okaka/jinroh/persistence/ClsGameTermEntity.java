package com.okaka.jinroh.persistence;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;


@Entity
@Table(name = "CLS_GAME_TERM")
public class ClsGameTermEntity {

    @Id
    @Column(name = "game_term")
    private String game_term;

    public ClsGameTermEntity() {
    }

    public ClsGameTermEntity(String game_term) {
        this.game_term = game_term;
    }

    public String getGame_term() {
        return game_term;
    }
}
