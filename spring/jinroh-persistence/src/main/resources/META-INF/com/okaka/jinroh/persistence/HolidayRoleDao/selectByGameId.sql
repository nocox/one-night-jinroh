SELECT ROLE.role_id, ROLE.role_name
FROM HOLIDAY_ROLE
         JOIN ROLE ON HOLIDAY_ROLE.role_id = ROLE.role_id
WHERE game_id = /*gameId*/'0'