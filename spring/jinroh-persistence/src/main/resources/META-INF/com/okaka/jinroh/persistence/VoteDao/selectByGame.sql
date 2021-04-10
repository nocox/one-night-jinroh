SELECT v.vote_id, v.game_participation_id, v.to_game_participation_id, v.peace_village_flg
FROM Vote as v
LEFT JOIN GAME_PARTICIPATION as gp ON gp.game_participation_id = v.game_participation_id
WHERE gp.game_id = /*gameId*/'1'
