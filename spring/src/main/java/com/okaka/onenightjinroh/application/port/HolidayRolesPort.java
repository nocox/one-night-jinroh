package com.okaka.onenightjinroh.application.port;

import com.okaka.onenightjinroh.application.domain.HolidayRoles;
import org.springframework.stereotype.Service;

@Service
public interface HolidayRolesPort {
    HolidayRoles findByGameId(Long gameId);
    void save(HolidayRoles holidayRoles);
}
