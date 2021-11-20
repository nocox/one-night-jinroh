select * from GAME_PARTICIPATION as gp
                  left join GAME on GAME.GAME_ID = gp.GAME_ID
                  left join USER on USER.USER_ID = gp.USER_ID
                  left join ROLE on ROLE.ROLE_ID = gp.ROLE_ID
where gp.GAME_ID = /*gameId*/1