package com.okaka.jinroh.persistence;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ConfigAutowireable
@Dao
public interface GameParticipationDao {
    @Select
    List<GameParticipation> selectGameParticipantsByGameId(Long gameId);

    @Select
    GameParticipation selectGameParticipant(Long gameId, Long userId);

    @Insert
    @Transactional
    int insert(GameParticipation gameParticipation);
}
