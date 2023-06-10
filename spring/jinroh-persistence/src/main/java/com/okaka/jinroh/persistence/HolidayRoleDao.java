package com.okaka.jinroh.persistence;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ConfigAutowireable
@Dao
public interface HolidayRoleDao {
    @Select
    List<HolidayRoleEntity> selectByGameId(Long gameId);

    @Insert
    @Transactional
    int insert(HolidayRoleEntity holidayRoleEntity);
}
