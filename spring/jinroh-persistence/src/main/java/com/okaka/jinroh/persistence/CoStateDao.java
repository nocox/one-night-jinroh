package com.okaka.jinroh.persistence;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ConfigAutowireable
@Dao
public interface CoStateDao {

    @Select
    List<CoStateEntity> selectByCoEventId(Long coEventId);

    @Insert
    @Transactional
    int insert(CoStateEntity coStateEntity);
}
