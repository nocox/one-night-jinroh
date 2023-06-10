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
public interface GameParticipantWithAllDao {

    @Select
    Optional<GameParticipantWithAllEntity> selectByParticipantId(Long participantId);

    @Select
    List<GameParticipantWithAllEntity> selectByGameId(Long gameId);

    @Insert
    @Transactional
    int insert(GameParticipantWithAllEntity gameParticipantWithAllEntity);
}
