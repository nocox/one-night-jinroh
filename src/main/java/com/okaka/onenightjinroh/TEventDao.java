package com.okaka.onenightjinroh;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.SelectType;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

@ConfigAutowireable
@Dao
public interface TEventDao {
    @Insert
    @Transactional
    int insert(TEvent tEvent);

    @Select(strategy = SelectType.STREAM)
    <R> R selectByUserIds(List<Integer> tUserIds, Function<Stream<TEvent>, R> func);
}
