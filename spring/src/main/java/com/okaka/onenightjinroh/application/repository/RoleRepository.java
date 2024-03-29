package com.okaka.onenightjinroh.application.repository;

import com.okaka.jinroh.persistence.RoleDao;
import com.okaka.jinroh.persistence.RoleEntity;
import com.okaka.jinroh.persistence.RoleSelectDao;
import com.okaka.onenightjinroh.application.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RoleRepository {
    @Autowired
    RoleDao roleDao;
    @Autowired
    RoleSelectDao roleSelectDao;

    public Role findByParticipationId(Long participationId) {
        final var roleEntity = roleDao.selectByParticipantId(List.of(participationId)).get(0);
        return Role.byRoleId(roleEntity.role_id, roleEntity.role_name);
    }

    public Map<Long, Role> toMapRoles() {
        Map<Long, Role> rolesMap = new HashMap<>();
        roleDao.selectAll().forEach(entity -> {
            rolesMap.put(entity.role_id, Role.byRoleId(entity.role_id, entity.role_name));
        });
        return rolesMap;
    }

    public Map<Long, RoleEntity> toMapRoleEntities() {
        Map<Long, RoleEntity> rolesMap = new HashMap<>();
        roleDao.selectAll().forEach(entity -> {
            rolesMap.put(entity.role_id, entity);
        });
        return rolesMap;
    }

    public static Role toDomains(RoleEntity entity) {
        return Role.byRoleId(entity.role_id, entity.role_name);
    }

    public Map<Long, Role> toMapRolesByRuleId(Long ruleId) {
        Map<Long, Role> rolesMap = new HashMap<>();
        roleSelectDao.selectRoleListByRuleId(ruleId).forEach(entity -> {
            Role role = Role.byRoleId(entity.role_id, entity.role_name);
            rolesMap.put(entity.role_id, role);
        });
        return rolesMap;
    }


}
