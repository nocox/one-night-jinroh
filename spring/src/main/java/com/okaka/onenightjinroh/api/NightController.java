package com.okaka.onenightjinroh.api;

import com.okaka.jinroh.persistence.Room;
import com.okaka.onenightjinroh.application.domain.ExistRoomValidate;
import com.okaka.onenightjinroh.application.service.night.GetNightTermIndexUseCase;
import com.okaka.onenightjinroh.application.service.night.NightTermIndexBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class NightController {
    @Autowired
    HttpSession session;

    @Autowired
    ExistRoomValidate existRoomValidate;

    @Autowired
    GetNightTermIndexUseCase getNightTermIndexUseCase;

    @RequestMapping(path = "/night-index")
    @CrossOrigin(origins = {"http://localhost:8081"}, allowCredentials = "true")
    NightTermIndexBean getNightTermIndex() {
        String uuid = session.getAttribute("room_uuid").toString();
        String strUserId = session.getAttribute("user_id").toString();
        Long userId = Long.valueOf(strUserId);

        Room room = existRoomValidate.existRoom(uuid).orElseThrow(IllegalArgumentException::new);

        return getNightTermIndexUseCase.getNightTermIndex(userId, room.room_id);
    }
}
