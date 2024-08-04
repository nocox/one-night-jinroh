package com.okaka.onenightjinroh.api;

import com.okaka.onenightjinroh.application.service.top.CreateRoomUseCase;
import com.okaka.onenightjinroh.application.service.top.CreateRoomUseCaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class TopController {
    @Autowired
    CreateRoomUseCase createRoomUseCase;

    @RequestMapping(path = "/create-room")
    int createRoom(HttpSession session) {
        CreateRoomUseCaseDto dto = createRoomUseCase.createRoom();

        session.setAttribute("user_id", dto.getUserEntity().user_id);
        session.setAttribute("room_uuid", dto.getRoom().uuid);
        return 0;
    }
}
