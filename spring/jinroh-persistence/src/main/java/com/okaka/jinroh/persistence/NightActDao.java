package com.okaka.jinroh.persistence;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@ConfigAutowireable
@Dao
public interface NightActDao {

    @Select
    List<NightActEntity> selectNightAct(Long gameId);

    @Select
    Optional<NightActEntity> selectNightActByParticipantId(Long participationId);

    @Insert
    @Transactional
    int insert(NightActEntity nightActEntity);
}
