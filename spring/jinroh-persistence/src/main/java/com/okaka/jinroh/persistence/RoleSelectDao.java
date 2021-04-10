package com.okaka.jinroh.persistence;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface RoleSelectDao {
    @Select
    List<RoleEntity> selectRoleListByRuleId(Long ruleId);
}