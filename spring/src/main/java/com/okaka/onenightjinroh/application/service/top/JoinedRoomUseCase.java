package com.okaka.onenightjinroh.application.service.top;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.okaka.jinroh.persistence.RoomEntity;
import com.okaka.jinroh.persistence.RoomParticipant;
import com.okaka.jinroh.persistence.RoomParticipantDao;
import com.okaka.jinroh.persistence.UserEntity;
import com.okaka.onenightjinroh.application.repository.UserRepository;
import com.okaka.onenightjinroh.application.validater.ExistRoomValidate;
import com.okaka.jinroh.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinedRoomUseCase {
    @Autowired
    UserDao userDao;

    @Autowired
    RoomParticipantDao roomParticipantDao;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExistRoomValidate existRoomValidate;

    public JoinedRoomUseCaseDto joinedRoom(String uuid) throws ParticipantLimitException, RoomNotExistException {
        RoomEntity roomEntity = existRoomValidate.existRoom(uuid).orElseThrow(() -> new RoomNotExistException("ルームが存在しません。"));
        
        int PARTICPANT_LIMIT = 6;
        if(PARTICPANT_LIMIT < roomParticipantDao.selectParticipantCount(roomEntity.room_id)){
            throw new ParticipantLimitException( String.format("参加人数は%s人までです。", PARTICPANT_LIMIT) );
        }

        UserEntity userEntity = new UserEntity();
        userEntity.user_name = getUseablePlayerNameOptions(roomEntity.room_id).get(0);
        userDao.insert(userEntity);

        RoomParticipant roomParticipant = new RoomParticipant();
        roomParticipant.room_id = roomEntity.room_id;
        roomParticipant.user_id = userEntity.user_id;
        roomParticipant.host_flg = false;
        roomParticipantDao.insert(roomParticipant);

        return new JoinedRoomUseCaseDto(userEntity);
    }

    private List<String> PLAYER_NAME_OPTIONS = Arrays.asList(
        "いぬ",
        "ねこ",
        "ひよこ",
        "ぺんぎん",
        "ねずみ",
        "さる",
        "ぱんだ",
        "くま",
        "あるぱか"
    );
    
    private List<String> getUsedPlayerNames(Long roomId) {
        return userRepository.findUsersByRoomId(roomId).stream()
        .map( user -> user.getUserName())
        .collect(Collectors.toList());
    }
    
    private List<String> getUseablePlayerNameOptions(Long roomId) {
        return PLAYER_NAME_OPTIONS.stream()
        .filter(option -> {
            return getUsedPlayerNames(roomId).stream()
            .noneMatch(name -> name.equals(option));
        })
        .collect(Collectors.toList());
    }
}
