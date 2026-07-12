package com.animevault.repository;

public class WorkRepositoryImpl {

    public static final String SEARCH_WORKS  =
            """
                SELECT
                    rank,
                    title,
                    anime_status AS animeStatus,
                    reading_format AS readingFormat,
                    reading_status AS readingStatus,
                    notes_status AS notesStatus,
                    is_active AS isActive
                FROM works
                WHERE
                    (:rank IS NULL OR rank = :rank)
                	AND (:title IS NULL OR title = :title)
                	AND (:isActive IS NULL OR is_active = :isActive)
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
                    reading_status,
                    notes_status
                )
                SELECT
                    id,
                    id,
                    :title,
                    :animeStatus,
                    :readingFormat,
                    :readingStatus,
                    :notesStatus
                FROM new_row
            """;

    public static final String UPDATE_WORK =
            """
                UPDATE works
                SET
                	"anime_status" = :animeStatus,
                	"reading_format" = :readingFormat,
                	"reading_status" = :readingStatus,
                	"notes_status" = :notesStatus
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
