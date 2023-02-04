package com.okaka.onenightjinroh.api;

import com.okaka.onenightjinroh.application.service.top.CreateRoomUseCase;
import com.okaka.onenightjinroh.application.service.top.CreateRoomUseCaseDto;
import com.okaka.onenightjinroh.application.service.top.JoinedRoomUseCase;
import com.okaka.onenightjinroh.application.service.top.JoinedRoomUseCaseDto;
import com.okaka.onenightjinroh.application.service.top.ParticipantLimitException;
import com.okaka.onenightjinroh.application.service.top.RoomNotExistException;
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
    JoinedRoomUseCase joinedRoomUseCase;

    @RequestMapping(path = "/create-room")
    int createRoom(HttpSession session) {
        CreateRoomUseCaseDto dto = createRoomUseCase.createRoom();

        session.setAttribute("user_id", dto.getUserEntity().user_id);
        session.setAttribute("room_uuid", dto.getRoom().uuid);
        return 0;
    }

    @RequestMapping(path = "/join-room")
    String joinedRoom(@RequestParam String uuid, HttpSession session) {
        try {
            JoinedRoomUseCaseDto dto = joinedRoomUseCase.joinedRoom(uuid);
            session.setAttribute("user_id", dto.getUserEntity().user_id);
            session.setAttribute("room_uuid", uuid);
            return "JOIN_SUCCESS";
        } catch(RoomNotExistException e) {
            return "ROOM_NOT_EXIST";
        } catch(ParticipantLimitException e) {
            return "PARTICPANT_LIMIT";
        }
    }
}
