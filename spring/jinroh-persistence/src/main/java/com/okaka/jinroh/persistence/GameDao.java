package com.okaka.jinroh.persistence;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

@ConfigAutowireable
@Dao
public interface GameDao {

    @Select
    GameEntity select(Long gameId);

    @Select
    GameEntity selectByRoomId(Long roomId);

    @Insert
    @Transactional
    int insert(GameEntity gameEntity);
}
