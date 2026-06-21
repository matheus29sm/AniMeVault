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
            """;

}
