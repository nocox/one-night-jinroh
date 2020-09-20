select
    T_USER.t_user_id as user_id,
    T_USER.t_user_name as user_name,
    T_PHONE.t_phone_number as phone_number
from T_USER
left join T_PHONE on T_USER.t_user_id = T_PHONE.t_user_id