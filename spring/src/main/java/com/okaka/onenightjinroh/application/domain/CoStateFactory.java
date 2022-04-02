package com.okaka.onenightjinroh.application.domain;

import com.okaka.onenightjinroh.application.repository.RoleRepository;
import com.okaka.onenightjinroh.application.service.talk.CoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CoStateFactory {

    @Autowired
    CoRepository coRepository;

    @Autowired
    RoleRepository roleRepository;

    public CoState create(Long gameId) {
        return coRepository.findCoEventId(gameId)
                .map(this::buildCoState)
                .orElse(CoState.buildEmpty());
    }

    private CoState buildCoState(Long coEventId) {
        Map<Long, Role> rolesMap = roleRepository.toMapRoles();

        List<CoState.Co> collect = coRepository.findCoState(coEventId).stream()
                .map(coDto -> new CoState.Co(coDto.getGameParticipationId(), rolesMap.get(coDto.getRoleId())))
                .collect(Collectors.toList());
        return CoState.build(collect);
    }
}
