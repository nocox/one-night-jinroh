select na.night_act_id, na.game_participation_id
FROM NIGHT_ACT as na
LEFT JOIN GAME_PARTICIPATION as gp ON gp.game_participation_id = na.game_participation_id
WHERE gp.game_id = /*gameId*/'0'