select * from GAME_PARTICIPATION as gp
left join GAME on GAME.GAME_ID = gp.GAME_ID
left join USER_PROFILE on USER_PROFILE.USER_ID = gp.USER_ID
left join ROLE on ROLE.ROLE_ID = gp.ROLE_ID
where gp.GAME_PARTICIPATION_ID = /*participantId*/'1'