package com.okaka.onenightjinroh.application.service.talk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import com.okaka.onenightjinroh.application.domain.CoState;
import com.okaka.onenightjinroh.application.domain.CoStateFactory;

@Service
public class CoUseCase {
    @Autowired
    CoStateFactory coStateFactory;
    @Autowired
    CoRepository coRepository;
    @Autowired
    SimpMessagingTemplate messagingTemplate;


    public void invoke(Long gameId, Long playerId, String roleName) {
        // 現在の状態を取得
        CoState coState = coStateFactory.create(gameId);

        // 現在の状態に対して変更を反映させる
        CoState updatedCoState = coState.update(playerId, roleName);

        // 反映させた変更の結果でも，現在と変化がない場合 -> 処理を終了
        if (coState.equalState(updatedCoState)) {
            return;
        }

        // 変更があった場合
        // DBにデータを追加.(coイベントとco状態を追加)
        Long eventId = coRepository.saveCoEvent(gameId, LocalDateTime.now());
        coRepository.saveCoState(eventId, updatedCoState);

        // ブロードキャスト
        CoStateBean coBean = CoStateBean.fromDomain(updatedCoState);
        messagingTemplate.convertAndSend("/topic/receive-co/" + gameId, coBean);
    }
}
