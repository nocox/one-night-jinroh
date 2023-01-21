package com.okaka.jinroh.persistence;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@ConfigAutowireable
@Dao
public interface RoomParticipantDao {

    @Select
    Long selectHostUserIdByRoom(Long roomId);

    @Select
    int selectParticipantCount(Long roomId);

    @Select
    Optional<RoomParticipantEntity> selectByUserId(Long userId);

    @Insert
    @Transactional
    int insert(RoomParticipantEntity roomParticipantEntity);

}
