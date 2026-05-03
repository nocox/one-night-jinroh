package com.okaka.onenightjinroh.domaimpl;

import com.okaka.jinroh.persistence.RoomParticipantDao;
import com.okaka.onenightjinroh.application.domain.RoomParticipant;
import com.okaka.onenightjinroh.application.repository.RoomParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoomParticipantRepositoryImpl implements RoomParticipantRepository {

    @Autowired
    RoomParticipantDao dao;

    @Override
    public Optional<RoomParticipant> findByUserId(Long userId) {
        return dao.selectByUserId(userId).map(entity ->
                new RoomParticipant(
                    entity.room_id,
                    entity.user_id,
                    entity.host_flg
                )
        );

    }
}
