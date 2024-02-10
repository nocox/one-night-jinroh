package com.okaka.jinroh.persistence;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ConfigAutowireable
@Dao
@Repository
public interface GameTermDao {

    @Select
    List<GameTermEntity> selectByGameId(Long gameId);

    @Insert
    @Transactional
    int insert(GameTermEntity gameTermEntity);
}
