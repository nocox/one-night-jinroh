package com.okaka.onenightjinroh;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.SelectType;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collector;

@ConfigAutowireable
@Dao
public interface TEventParticipantDao {
    @Insert
    @Transactional
    int insert(TEventParticipant tEventParticipant);

    @Select(strategy = SelectType.COLLECT)
    <RESULT> RESULT selectByUserIds(List<Integer> tUserIds, Collector<TEventParticipant, ?, RESULT> collector);
}
