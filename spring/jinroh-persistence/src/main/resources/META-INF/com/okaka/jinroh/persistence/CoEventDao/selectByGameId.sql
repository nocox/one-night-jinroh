select ce.CO_EVENT_ID from CO_EVENT as ce
where ce.GAME_ID = /*gameId*/1
order by ce.CO_TM DESC
LIMIT 1