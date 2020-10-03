SELECT
    T_USER.t_user_id as user_id,
    T_USER.t_user_name as user_name,
    T_PHONE.t_phone_number as phone_number
FROM
    T_USER
LEFT JOIN T_PHONE on T_USER.t_user_id = T_PHONE.t_user_id
WHERE
    T_USER.t_user_id IN /*tUserIds*/(1, 2)