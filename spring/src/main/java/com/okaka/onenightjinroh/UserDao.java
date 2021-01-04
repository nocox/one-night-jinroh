package com.okaka.onenightjinroh;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ConfigAutowireable
@Dao
public interface UserDao {

    @Select
    int selectCount();

    @Select
    List<User> selectByRoom(Long roomId);

    @Insert
    @Transactional
    int insert(User user);
}
