package com.okaka.onenightjinroh.domaimpl;

import com.okaka.jinroh.persistence.HolidayRoleDao;
import com.okaka.jinroh.persistence.HolidayRoleEntity;
import com.okaka.jinroh.persistence.RoleDao;
import com.okaka.onenightjinroh.application.domain.HolidayRoles;
import com.okaka.onenightjinroh.application.domain.Role;
import com.okaka.onenightjinroh.application.repository.HolidayRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HolidayRolesRepositoryImpl implements HolidayRolesRepository {
    @Autowired
    private HolidayRoleDao dao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public HolidayRoles findByGameId(Long gameId) {
        List<Long> roleIds = dao.selectByGameId(gameId).stream()
                .map(entity -> entity.role_id)
                .collect(Collectors.toList());
        List<Role> roles = roleDao.selectByRoleIds(roleIds).stream()
                .map(entity -> Role.byRoleId(entity.role_id, entity.role_name))
                .collect(Collectors.toList());
        return new HolidayRoles(gameId, roles);
    }

    @Override
    public void save(HolidayRoles holidayRoles) {
        holidayRoles.getRoles().forEach(role -> {
            HolidayRoleEntity holidayRoleEntity = new HolidayRoleEntity();
            holidayRoleEntity.setRole_id(role.getRoleId());
            holidayRoleEntity.setGame_id(holidayRoles.getGameId());
            dao.insert(holidayRoleEntity);
        });
    }
}
