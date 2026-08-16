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
                    notes_status
)
SELECT
    id,
    9999999,
    'TESTE INTEGRAT',
    'ONGOING',
    'MANGA',
    'ONGOING',
    'READING',
    'READING'
FROM new_row;