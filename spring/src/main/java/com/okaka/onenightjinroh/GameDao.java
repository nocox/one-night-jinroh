package com.okaka.onenightjinroh;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

@ConfigAutowireable
@Dao
public interface GameDao {

    @Select
    Game selectByRoomId(Long roomId);

    @Insert
    @Transactional
    int insert(Game game);
}
