WITH new_row AS (
    SELECT nextval('works_id_seq') AS id
)
INSERT INTO works (
                    id,
                    rank,
                    title,
                    anime_status,
                    reading_format,
                    reading_format_status,
                    user_reading_status,
                    notes_status,
                    is_active
)
SELECT
    id,
    9999999,
    'TESTE INTEGRAT INACTIVE',
    'ONGOING',
    'MANGA',
    'ONGOING',
    'READING',
    'READING',
    false
FROM new_row;