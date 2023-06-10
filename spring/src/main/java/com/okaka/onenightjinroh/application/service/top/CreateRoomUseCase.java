package com.okaka.onenightjinroh.application.service.top;

import com.okaka.jinroh.persistence.*;
import com.okaka.onenightjinroh.application.domain.Room;
import com.okaka.onenightjinroh.application.domain.RoomStatus;
import com.okaka.onenightjinroh.application.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CreateRoomUseCase {
    private final RoomRepository roomRepository;
    private final UserDao userDao;
    private final RoomParticipantDao roomParticipantDao;

    public CreateRoomUseCase(RoomRepository roomRepository, RoomDao roomDao, UserDao userDao, RoomParticipantDao roomParticipantDao) {
        this.roomRepository = roomRepository;
        this.userDao = userDao;
        this.roomParticipantDao = roomParticipantDao;
    }

    @Transactional
    public CreateRoomUseCaseDto createRoom(){
        String uuid = UUID.randomUUID().toString();
        roomRepository.save(new Room(null, uuid, null, RoomStatus.Ready));
        Room room = roomRepository.findByUUID(uuid).orElseThrow();

        UserEntity userEntity = new UserEntity();
        userEntity.user_name = "ホストのふくろう";
        userDao.insert(userEntity);

        RoomParticipantEntity roomParticipantEntity = new RoomParticipantEntity();
        roomParticipantEntity.room_id = room.getRoomId();
        roomParticipantEntity.user_id = userEntity.user_id;
        roomParticipantEntity.host_flg = true;
        roomParticipantDao.insert(roomParticipantEntity);

        return new CreateRoomUseCaseDto(userEntity, room);
    }
}
