package com.okaka.onenightjinroh.application.service.night;

import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.NightAct;
import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.domain.UranaishiNightAct;
import com.okaka.onenightjinroh.application.repository.GameParticipantRepository;
import com.okaka.onenightjinroh.application.repository.NightActRepository;
import com.okaka.onenightjinroh.application.repository.RoleRepository;
import com.okaka.onenightjinroh.application.repository.UranaishiNightActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoneNightTermActUseCase {
    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @Autowired
    GameParticipantRepository gameParticipantRepository;

    @Autowired
    NightActRepository nightActRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UranaishiNightActRepository uranaishiNightActRepository;


    public void done(Long gameParticipantId, Long gameId){
        nightActRepository.create(gameParticipantId);
        Role role = roleRepository.findByParticipationId(gameParticipantId);
        if (role instanceof Role.Uranaishi && uranaishiNightActRepository.findByFromParticipationId(gameParticipantId).isEmpty()) {
            uranaishiNightActRepository.save(UranaishiNightAct.byNotSelected(gameParticipantId));
        }
        if (allDoneNightAct(gameId)) {
            System.out.println("all done !!!");
            messagingTemplate.convertAndSend("/topic/" + gameId, "test");
        }
    }

    private boolean allDoneNightAct(Long gameId){
        List<GameParticipant> gameParticipants = gameParticipantRepository.findByGameId(gameId);
        List<NightAct> nightActs = nightActRepository.findByGameId(gameId);
        return gameParticipants.stream()
                .map(GameParticipant::getGameParticipationId)
                .allMatch(gameParticipantId -> nightActs.stream()
                        .map(NightAct::getGameParticipant)
                        .map(GameParticipant::getGameParticipationId)
                        .collect(Collectors.toList())
                        .contains(gameParticipantId));
    }
}
