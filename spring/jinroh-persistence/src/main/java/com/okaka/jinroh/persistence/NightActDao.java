package com.okaka.jinroh.persistence;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

@ConfigAutowireable
@Dao
public interface NightActDao {

    @Insert
    @Transactional
    int insert(NightAct nightAct);
}
