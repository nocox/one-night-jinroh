SELECT v.vote_id, v.game_participation_id, v.to_game_participation_id, v.peace_village_flg
FROM Vote as v
WHERE v.game_participation_id = /*voterId*/'1'