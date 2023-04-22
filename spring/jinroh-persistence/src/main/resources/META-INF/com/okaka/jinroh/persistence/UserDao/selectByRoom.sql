SELECT u.user_id, u.user_name FROM ROOM_PARTICIPANT as rp
LEFT JOIN USER_PROFILE as u ON u.user_id = rp.user_id
WHERE rp.room_id = /*roomId*/'1'
