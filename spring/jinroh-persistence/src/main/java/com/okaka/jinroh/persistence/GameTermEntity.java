package com.okaka.jinroh.persistence;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table(name = "GAME_TERM")
public class GameTermEntity {

    @Id
    @Column(name = "game_id")
    private Long game_id;

    private String game_term;

    public GameTermEntity() {
    }

    public GameTermEntity(Long game_id, String game_term) {
        this.game_id = game_id;
        this.game_term = game_term;
    }

    public Long getGame_id() {
        return game_id;
    }

    public String getGame_term() {
        return game_term;
    }

    public void setGame_id(Long game_id) {
        this.game_id = game_id;
    }

    public void setGame_term(String game_term) {
        this.game_term = game_term;
    }
}
