package com.okaka.onenightjinroh.application.domain;

import com.okaka.jinroh.persistence.RoleDao;
import com.okaka.jinroh.persistence.RoleEntity;
import com.okaka.jinroh.persistence.RoleSelectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RoleRepository {
    @Autowired
    RoleDao roleDao;
    @Autowired
    RoleSelectDao roleSelectDao;

    public Map<Long, Role> toMapRoles() {
        Map<Long, Role> rolesMap = new HashMap<>();
        roleDao.selectAll().forEach(entity -> {
            Role role = new Role(entity.role_id);
            role.setRoleName(entity.role_name);
            rolesMap.put(entity.role_id, role);
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
        Role role = new Role(entity.role_id);
        role.setRoleName(entity.role_name);
        return role;
    }

    public Map<Long, Role> toMapRolesByRuleId(Long ruleId) {
        Map<Long, Role> rolesMap = new HashMap<>();
        roleSelectDao.selectRoleListByRuleId(ruleId).forEach(entity -> {
            Role role = new Role(entity.role_id);
            role.setRoleName(entity.role_name);
            rolesMap.put(entity.role_id, role);
        });
        return rolesMap;
    }


}
