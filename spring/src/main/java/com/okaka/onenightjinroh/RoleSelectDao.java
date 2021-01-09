package com.okaka.onenightjinroh;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface RoleSelectDao {
    @Select
    List<Role> selectRoleListByRuleId(Long ruleId);
}
