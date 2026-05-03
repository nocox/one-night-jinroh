select *
FROM GAME as g
WHERE g.game_id = /*gameId*/'0'
ORDER BY g.game_id DESC
LIMIT 1