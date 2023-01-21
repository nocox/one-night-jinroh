package com.okaka.onenightjinroh.application.repository;

import com.okaka.onenightjinroh.application.domain.RoomParticipant;

import java.util.Optional;

public interface RoomParticipantRepository {
    Optional<RoomParticipant> findByUserId(Long userId);
}
