package com.animevault.repository;

public class WorkRepositoryImpl {

    public static final String SEARCH_WORKS  =
            """
                SELECT
                    rank,
                    title,
                    anime_status AS animeStatus,
                    reading_format AS readingFormat,
                    reading_format_status AS readingStatus,
                    user_reading_status AS userStatus,
                    notes_status AS notesStatus,
                    is_active AS isActive
                FROM works
                WHERE
                    (:rank IS NULL OR rank = :rank)
                	AND (:title IS NULL OR title = :title)
                	AND is_active = :isActive
            """;

    public static final String REGISTER_NEW_WORK =
            """
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
                    id,
                    :title,
                    CAST(:animeStatus AS anime_status),
                    CAST(:readingFormat AS reading_format),
                    CAST(:readingStatus AS reading_format_status),
                    CAST(:userStatus AS user_reading_status),
                    CAST(:notesStatus AS notes_status)
                FROM new_row
            """;

    public static final String UPDATE_WORK =
            """
                UPDATE works
                SET
                	anime_status = CAST(:animeStatus AS anime_status),
                    reading_format = CAST(:readingFormat AS reading_format),
                    reading_format_status = CAST(:readingStatus AS reading_format_status),
                    user_reading_status = CAST(:userStatus AS user_reading_status),
                    notes_status = CAST(:notesStatus AS notes_status)
                WHERE
                	(:rank IS NULL OR rank = :rank)
                	AND (:title IS NULL OR title = :title)
            """;

    public static final String DEACTIVATE_WORK =
            """
                UPDATE works
                SET is_active = FALSE
                WHERE rank = :rank
            """;

    public static final String ACTIVATE_WORK =
            """
                UPDATE works
                SET is_active = TRUE
                WHERE rank = :rank
            """;
}
