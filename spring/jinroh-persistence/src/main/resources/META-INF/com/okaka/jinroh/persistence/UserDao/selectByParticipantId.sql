SELECT u.user_id, u.user_name
FROM GAME_PARTICIPATION as gp
         LEFT JOIN USER_PROFILE as u ON u.user_id = gp.user_id
WHERE gp.game_participation_id = /*participantId*/'1'