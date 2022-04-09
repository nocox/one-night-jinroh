package com.okaka.jinroh.persistence;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@ConfigAutowireable
@Dao
public interface KaitoNightActDao {
    @Select
    Optional<KaitoNightActEntity> selectByFromParticipationId(Long participationId);

    @Select
    Optional<KaitoNightActEntity> selectByGameId(Long gameId);

    @Insert
    @Transactional
    int insert(KaitoNightActEntity kaitoNightActEntity);
}
