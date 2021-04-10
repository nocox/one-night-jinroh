package com.okaka.jinroh.persistence;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface RoleDao {
    @Select
    List<RoleEntity> selectAll();
}