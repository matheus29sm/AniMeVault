package com.animevault.dto;

import com.animevault.enums.AnimeStatus;
import com.animevault.enums.NotesStatus;
import com.animevault.enums.ReadingFormat;
import com.animevault.enums.ReadingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

public class WorkRequestDTO {

    @Data
    public static class NewWork{
        @Schema(description = "Title of the work")
        private String title;

        @Schema(description = "Current anime status")
        private AnimeStatus animeStatus;

        @Schema(description = "Format of the reading material")
        private ReadingFormat readingFormat;

        @Schema(description = "Current reading status")
        private ReadingStatus readingStatus;

        @Schema(description = "Notes or remarks status")
        private NotesStatus notesStatus;
    }

    @Data
    public static class UpdateWork{
        @Schema(description = "Current anime status")
        private AnimeStatus animeStatus;

        @Schema(description = "Format of the reading material")
        private ReadingFormat readingFormat;

        @Schema(description = "Current reading status")
        private ReadingStatus readingStatus;

        @Schema(description = "Notes or remarks status")
        private NotesStatus notesStatus;
    }
}
