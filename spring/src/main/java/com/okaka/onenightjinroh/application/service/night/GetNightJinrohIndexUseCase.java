package com.okaka.onenightjinroh.application.service.night;

import com.okaka.onenightjinroh.api.bean.NightJinrohIndexBean;
import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetNightJinrohIndexUseCase {

    @Autowired
    RoleRepository roleRepository;

    public NightJinrohIndexBean invoke(final Long participantId, final Long gameId) {
        Role role = roleRepository.findByParticipationId(participantId);
        if (!role.isJinroh()) {
            throw new IllegalArgumentException();
        }

        // TODO: そのゲームにおいての人狼を返す
        return new NightJinrohIndexBean();
    }
}
