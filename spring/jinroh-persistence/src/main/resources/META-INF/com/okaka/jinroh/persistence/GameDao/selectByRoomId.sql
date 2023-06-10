select *
FROM GAME as g
WHERE g.room_id = /*roomId*/'0'
ORDER BY g.game_id DESC
LIMIT 1