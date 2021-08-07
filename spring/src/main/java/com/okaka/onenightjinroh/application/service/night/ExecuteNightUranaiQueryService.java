package com.okaka.onenightjinroh.application.service.night;

import com.okaka.jinroh.persistence.RoleDao;
import com.okaka.jinroh.persistence.RoleEntity;
import com.okaka.onenightjinroh.application.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExecuteNightUranaiQueryService {
    @Autowired
    RoleDao roleDao;

    List<Role> find(List<Long> participantIds) {
        List<RoleEntity> roleEntities = roleDao.selectByParticipantId(participantIds);
        return roleEntities.stream()
                .map(roleEntity -> new Role(roleEntity.role_id, roleEntity.role_name))
                .collect(Collectors.toList());
    }
}
