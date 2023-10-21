package com.okaka.jinroh.persistence;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ConfigAutowireable
@Dao
@Repository
public interface RoleDao {
    @Select
    List<RoleEntity> selectAll();

    @Select
    List<RoleEntity> selectByRoleIds(List<Long> roleIds);

    @Select
    List<RoleEntity> selectByParticipantId(List<Long> participantIds);

    @Insert
    @Transactional
    int insert(RoleEntity roleEntity);
}
