select r.role_id, r.role_name
FROM ROLE_SELECT as rs
LEFT JOIN ROLE as r ON rs.role_id = r.role_id
WHERE rs.rule_id = /*ruleId*/'0'