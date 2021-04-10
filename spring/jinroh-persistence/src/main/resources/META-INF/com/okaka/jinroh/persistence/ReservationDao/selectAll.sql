SELECT
    id,
    name
FROM reservation
WHERE delete_flg = false
ORDER BY name ASC