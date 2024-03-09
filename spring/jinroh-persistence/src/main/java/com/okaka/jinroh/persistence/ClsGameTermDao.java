package com.okaka.jinroh.persistence;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

@ConfigAutowireable
@Dao
public interface ClsGameTermDao {

    @Select
    boolean exist(String termName);

    @Insert
    @Transactional
    int insert(ClsGameTermEntity clsGameTermEntity);

}
