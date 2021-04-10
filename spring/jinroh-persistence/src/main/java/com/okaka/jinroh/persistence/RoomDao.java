package com.okaka.jinroh.persistence;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@ConfigAutowireable
@Dao
public interface RoomDao {

    @Select
    Optional<RoomEntity> selectRoomByUUID(String uuid);

    @Insert
    @Transactional
    int insert(RoomEntity roomEntity);
}
