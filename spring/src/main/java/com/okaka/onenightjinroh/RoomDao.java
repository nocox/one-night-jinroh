package com.okaka.onenightjinroh;

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
    Optional<Room> selectRoomByUUID(String uuid);

    @Insert
    @Transactional
    int insert(Room room);
}
