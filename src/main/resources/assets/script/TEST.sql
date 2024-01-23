SELECT food_type, favorites
FROM REST_INFO ri1
WHERE (
  SELECT COUNT(DISTINCT ri2.favorites)
  FROM REST_INFO ri2
  WHERE ri2.favorites > ri1.favorites
) = 1
ORDER BY food_type;

