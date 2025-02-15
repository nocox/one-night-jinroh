select g.game_id as gameId,
       g.room_id as roomId,
       gt.game_term as gameTerm,
       g.rule_id as ruleId,
       rule.rule_name as ruleName,
       role.role_id as roleId, -- 複数
       role.role_name as roleName -- 複数
FROM GAME as g
         join ROOM as room on g.room_id = room.room_id
         join RULE as rule on g.rule_id = rule.rule_id
         join ROLE_SELECT as rs on rule.rule_id = rs.rule_id
         join ROLE as role on rs.role_id = role.role_id
         join game_term as gt on g.game_id = gt.game_id
WHERE g.room_id = /*roomId*/'0'
ORDER BY g.game_id DESC