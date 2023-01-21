package com.okaka.onenightjinroh.api;

import com.okaka.jinroh.persistence.RoomEntity;
import com.okaka.onenightjinroh.application.domain.Room;
import com.okaka.onenightjinroh.application.service.room.GameStartWebSocketBean;
import com.okaka.onenightjinroh.application.service.room.GetRoomIndexUseCase;
import com.okaka.onenightjinroh.application.service.room.RoomIndexBean;
import com.okaka.onenightjinroh.application.service.room.StartGameUseCase;
import com.okaka.onenightjinroh.application.validater.ExistRoomValidate;
import com.okaka.onenightjinroh.application.validater.StartGameValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    RoomIndexBean getRoom() {
        String uuid = session.getAttribute("room_uuid").toString();
        String strUserId = session.getAttribute("user_id").toString();
        Long userId = Long.valueOf(strUserId);

        return getRoomIndexUseCase.getRoomIndex(userId, uuid);
    }

    @RequestMapping(path = "/game-start")
    int startGame() {
        String uuid = session.getAttribute("room_uuid").toString();
        Long userId = Long.valueOf(session.getAttribute("user_id").toString());

        Room room = existRoomValidate.existRoom(uuid).orElseThrow(IllegalArgumentException::new);
        if (startGameValidate.run(userId, room.getRoomId())) {
            throw new IllegalArgumentException();
        }

        GameStartWebSocketBean gameStartWebSocketBean = startGameUseCase.startGame(room.getRoomId(), userId);

        // ここでブロードキャストする
        messagingTemplate.convertAndSend("/topic/" + room.uuid, gameStartWebSocketBean);
        return 0;
    }
}
