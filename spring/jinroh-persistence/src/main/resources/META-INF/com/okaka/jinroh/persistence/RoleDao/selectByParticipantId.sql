SELECT r.ROLE_ID, r.ROLE_NAME
FROM GAME_PARTICIPATION AS gp
LEFT JOIN ROLE AS r ON gp.ROLE_ID = r.ROLE_ID
WHERE gp.GAME_PARTICIPATION_ID IN /*participantIds*/(1, 2)