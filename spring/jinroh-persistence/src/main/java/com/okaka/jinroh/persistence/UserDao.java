package com.okaka.jinroh.persistence;

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
    List<UserEntity> selectByRoom(Long roomId);

    @Select
    List<UserEntity> selectByGame(Long gameId);

    @Select
    UserEntity selectByParticipantId(Long participantId);

    @Insert
    @Transactional
    int insert(UserEntity userEntity);
}
