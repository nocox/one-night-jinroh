package com.okaka.onenightjinroh.application.service.night;

import com.okaka.onenightjinroh.application.domain.*;
import com.okaka.onenightjinroh.application.repository.*;
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

    @Autowired
    GameRepository gameRepository;

    public void done(Long gameParticipantId, Long gameId){
        nightActRepository.create(gameParticipantId);
        Role role = roleRepository.findByParticipationId(gameParticipantId);
        if (role instanceof Role.Uranaishi && uranaishiNightActRepository.findByParticipationId(gameParticipantId).isEmpty()) {
            uranaishiNightActRepository.save(UranaishiNightAct.byNotSelected(gameParticipantId));
        }
        if (allDoneNightAct(gameId)) {
            Game game = gameRepository.find(gameId);
            Game changedGame = game.changeTerm(GameTerm.TALK);
            gameRepository.save(changedGame);
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
