select rp.user_id
FROM ROOM_PARTICIPANT as rp
WHERE rp.room_id = /*roomId*/'0'
AND rp.host_flg = true
