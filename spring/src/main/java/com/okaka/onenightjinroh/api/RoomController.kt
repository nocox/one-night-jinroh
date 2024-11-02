package com.okaka.onenightjinroh.api;

import com.okaka.onenightjinroh.application.domain.Room;
import com.okaka.onenightjinroh.application.exception.RoomNotExistException;
import com.okaka.onenightjinroh.application.service.room.*;
import com.okaka.onenightjinroh.application.validater.ExistRoomValidate;
import com.okaka.onenightjinroh.application.validater.StartGameValidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpSession;

@RestController
public class RoomController {

    private static final Logger log = LoggerFactory.getLogger(RoomController.class);
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
    private FinishRoomUseCase finishRoomUseCase;
    @Autowired
    private View error;

    @RequestMapping(path = "/room-index")
    RoomIndexBean getRoom() {
        String uuid = session.getAttribute("room_uuid").toString();
        String strUserId = session.getAttribute("user_id").toString();
        Long userId = Long.valueOf(strUserId);

        return getRoomIndexUseCase.getRoomIndex(userId, uuid);
    }

    @RequestMapping(path = "/game-start")
    String startGame() {
        String uuid = session.getAttribute("room_uuid").toString();
        Long userId = Long.valueOf(session.getAttribute("user_id").toString());

        Room room = existRoomValidate.existRoom(uuid).orElseThrow(IllegalArgumentException::new);

        try {
            GameStartWebSocketBean gameStartWebSocketBean = startGameUseCase.startGame(room.getRoomId(), userId);
            // ここでブロードキャストする
            messagingTemplate.convertAndSend("/topic/" + room.uuid, gameStartWebSocketBean);
            return "SUCCESS";
        } catch (NotEnoughParticipantsException e) {
            return "NOT_ENOUGH_PARTICIPANTS";
        }
    }
}
