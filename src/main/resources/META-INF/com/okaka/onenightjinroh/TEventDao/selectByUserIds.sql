SELECT
    e.t_event_id,
    e.t_event_title,
    e.t_event_detail
FROM T_EVENT as e
LEFT JOIN T_EVENT_PARTICIPANT as ep ON ep.t_event_id = e.t_event_id
WHERE ep.t_user_id IN /*tUserIds*/(1, 2)