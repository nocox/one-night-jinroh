package com.okaka.onenightjinroh.application.repository;

import com.okaka.onenightjinroh.application.domain.Room;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository {
    void save(Room room);
    Optional<Room> findByUUID(String uuid);
}
