package com.okaka.jinroh.persistence;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@ConfigAutowireable
@Dao
public interface CoEventDao {
    @Select
    Optional<Long> selectByGameId(Long gameId);

    @Insert
    @Transactional
    int insert(CoEventEntity coEventEntity);
}
