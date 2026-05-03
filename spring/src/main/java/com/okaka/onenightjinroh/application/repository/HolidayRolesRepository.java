package com.okaka.onenightjinroh.application.repository;

import com.okaka.onenightjinroh.application.domain.HolidayRoles;
import org.springframework.stereotype.Service;

@Service
public interface HolidayRolesRepository {
    HolidayRoles findByGameId(Long gameId);
    void save(HolidayRoles holidayRoles);
}
