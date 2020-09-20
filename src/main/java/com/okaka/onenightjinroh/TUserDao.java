package com.okaka.onenightjinroh;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ConfigAutowireable
@Dao
public interface TUserDao {

    @Select
    List<TUser> selectTUserAll();

    @Select
    List<TUserProfile> selectTUserProfileAll();

    @Select
    List<TUserProfile> selectEventParticipantAll(Integer tEventId);

    @Insert
    @Transactional
    int insert(TUser tUser);
}
