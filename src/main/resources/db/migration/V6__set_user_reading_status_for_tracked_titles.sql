-- Update user reading status for titles currently being tracked
UPDATE works
SET user_reading_status = CASE title
    WHEN 'One Piece' THEN 'PAUSED'
    WHEN 'Naruto' THEN 'FINISHED'
    WHEN 'Dragon Ball' THEN 'PAUSED'
    WHEN 'Bleach' THEN 'FINISHED'
    WHEN 'Black Clover' THEN 'FINISHED'
    WHEN 'Boku no Hero Academia' THEN 'FINISHED'
    WHEN 'Hunter x Hunter' THEN 'DROPPED'
    WHEN 'One Punch-Man' THEN 'DROPPED'
    WHEN 'Fairy Tail' THEN 'DROPPED'
    WHEN 'Shingeki no Kyojin' THEN 'FINISHED'
    WHEN 'Nanatsu no Taizai' THEN 'DROPPED'
    WHEN 'Rosario + Vampire' THEN 'FINISHED'
    WHEN 'Yakusoku no Neverland' THEN 'DROPPED'
    ELSE user_reading_status
END
WHERE title IN ('One Piece', 'Naruto', 'Fairy Tail', 'Dragon Ball', 'Bleach', 'Black Clover',
    'Boku no Hero Academia', 'Hunter x Hunter', 'One Punch-Man', 'Shingeki no Kyojin',
     'Nanatsu no Taizai', 'Rosario + Vampire', 'Yakusoku no Neverland');