SELECT u.user_id, u.user_name
FROM GAME_PARTICIPATION as gp
LEFT JOIN USER as u ON u.user_id = gp.user_id
WHERE gp.game_id = /*gameId*/'1'
