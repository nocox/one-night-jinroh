package com.okaka.onenightjinroh.application.service.night;

import com.okaka.onenightjinroh.api.bean.NightJinrohIndexBean;
import com.okaka.onenightjinroh.application.domain.GameParticipant;
import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.domain.User;
import com.okaka.onenightjinroh.application.repository.GameParticipantRepository;
import com.okaka.onenightjinroh.application.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetNightJinrohIndexUseCase {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    GameParticipantRepository gameParticipantRepository;

    public NightJinrohIndexBean invoke(final Long participantId, final Long gameId) {
        Role role = roleRepository.findByParticipationId(participantId);
        if (!role.isJinroh()) {
            throw new IllegalArgumentException();
        }

        List<GameParticipant> participants = gameParticipantRepository.findByGameId(gameId);
        List<String> participantPlayerNames = participants.stream()
                .filter(participant -> participant.getRole().isJinroh())
                .filter(participant -> !participant.getGameParticipationId().equals(participantId))
                .map(GameParticipant::getUser)
                .map(User::getUserName)
                .collect(Collectors.toList());

        return new NightJinrohIndexBean(participantPlayerNames);
    }
}
