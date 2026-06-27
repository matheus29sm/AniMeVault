package com.animevault.repository;

public class WorkRepositoryImpl {

    public static final String SEARCH_WORKS  =
            """
                SELECT
                    id,
                    rank,
                    title,
                    anime_status AS animeStatus,
                    reading_format AS readingFormat,
                    reading_status AS readingStatus,
                    notes_status AS notesStatus
                FROM works
                WHERE
                    (:rank IS NULL OR rank = :rank)
                	AND (:title IS NULL OR title = :title)
            """;

    public static final String REGISTER_NEW_WORK =
            """
                INSERT INTO works (
                    id,
                    rank,
                    title,
                    anime_status,
                    reading_format,
                    reading_status,
                    notes_status
                )
                VALUES (
                    nextval('works_id_seq'::regclass),
                    currval('works_id_seq'::regclass),
                    :title,
                    :animeStatus,
                    :readingFormat,
                    :readingStatus,
                    :notesStatus
                );
            """;

}
