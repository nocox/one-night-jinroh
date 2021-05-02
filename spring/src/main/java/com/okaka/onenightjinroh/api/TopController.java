package com.okaka.onenightjinroh.api;

import com.okaka.jinroh.persistence.RoomEntity;
import com.okaka.onenightjinroh.application.domain.ExistRoomValidate;
import com.okaka.onenightjinroh.application.service.top.CreateRoomUseCase;
import com.okaka.onenightjinroh.application.service.top.CreateRoomUseCaseDto;
import com.okaka.onenightjinroh.application.service.top.JoinedRoomUseCase;
import com.okaka.onenightjinroh.application.service.top.JoinedRoomUseCaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class TopController {
    @Autowired
    CreateRoomUseCase createRoomUseCase;

    @Autowired
    ExistRoomValidate existRoomValidate;

    @Autowired
    JoinedRoomUseCase joinedRoomUseCase;

    @RequestMapping(path = "/create-room")
    int createRoom(HttpSession session) {
        CreateRoomUseCaseDto dto = createRoomUseCase.createRoom();

        session.setAttribute("user_id", dto.getUserEntity().user_id);
        session.setAttribute("room_uuid", dto.getRoomEntity().uuid);
        return 0;
    }

    @RequestMapping(path = "/join-room")
    int joinedRoom(@RequestParam String uuid, HttpSession session) {
        RoomEntity roomEntity = existRoomValidate.existRoom(uuid).orElseThrow(IllegalArgumentException::new);
        JoinedRoomUseCaseDto dto = joinedRoomUseCase.joinedRoom(roomEntity.room_id);

        session.setAttribute("user_id", dto.getUserEntity().user_id);
        session.setAttribute("room_uuid", uuid);
        return 0;
    }
}
