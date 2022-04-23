package com.okaka.onenightjinroh.application.service.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReturnRoomUseCase {
    @Autowired
    SimpMessagingTemplate messagingTemplate;

    public void invoke(Long gameId) {
        messagingTemplate.convertAndSend("/topic/return-room/" + gameId, "");
    }
}
