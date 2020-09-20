select
    u.t_user_id as user_id,
    u.t_user_name as user_name,
    p.t_phone_number as phone_number
from T_EVENT_PARTICIPANT as ep
left join T_USER as u on ep.t_user_id = u.t_user_id
left join T_PHONE as p on p.t_user_id = u.t_user_id
where ep.t_event_id = /* tEventId */1