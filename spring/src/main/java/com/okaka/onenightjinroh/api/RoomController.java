package com.okaka.onenightjinroh.api;

import com.okaka.jinroh.persistence.RoomEntity;
import com.okaka.onenightjinroh.application.service.room.GameStartWebSocketBean;
import com.okaka.onenightjinroh.application.service.room.RoomIndexBean;
import com.okaka.onenightjinroh.application.domain.ExistRoomValidate;
import com.okaka.onenightjinroh.application.domain.StartGameValidate;
import com.okaka.onenightjinroh.application.service.room.GetRoomIndexUseCase;
import com.okaka.onenightjinroh.application.service.room.StartGameUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class RoomController {

    @Autowired
    HttpSession session;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    GetRoomIndexUseCase getRoomIndexUseCase;

    @Autowired
    private ExistRoomValidate existRoomValidate;

    @Autowired
    private StartGameUseCase startGameUseCase;

    @Autowired
    private StartGameValidate startGameValidate;

    @RequestMapping(path = "/room-index")
    @CrossOrigin(origins = {"http://localhost:8081"}, allowCredentials = "true")
    RoomIndexBean getRoom() {
        String uuid = session.getAttribute("room_uuid").toString();
        String strUserId = session.getAttribute("user_id").toString();
        Long userId = Long.valueOf(strUserId);

        return getRoomIndexUseCase.getRoomIndex(userId, uuid);
    }

    @RequestMapping(path = "/game-start")
    @CrossOrigin(origins = {"http://localhost:8081"}, allowCredentials = "true")
    int startGame() {
        String uuid = session.getAttribute("room_uuid").toString();
        Long userId = Long.valueOf(session.getAttribute("user_id").toString());

        RoomEntity roomEntity = existRoomValidate.existRoom(uuid).orElseThrow(IllegalArgumentException::new);
        if (startGameValidate.run(userId, roomEntity.room_id)) {
            throw new IllegalArgumentException();
        }

        GameStartWebSocketBean gameStartWebSocketBean = startGameUseCase.startGame(roomEntity.room_id, userId);

        // ここでブロードキャストする
        this.messagingTemplate.convertAndSend("/topic/" + roomEntity.uuid, gameStartWebSocketBean);
        return 0;
    }
}
