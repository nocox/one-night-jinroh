package com.okaka.jinroh.persistence;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@ConfigAutowireable
@Dao
@Repository
public interface RuleDao {
    @Select
    boolean existRule(String ruleName);

    @Select
    RuleEntity select(Long roomId);

    @Insert
    @Transactional
    int insert(RuleEntity ruleEntity);
}
