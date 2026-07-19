package com.animevault.dto;

import com.animevault.enums.AnimeStatus;
import com.animevault.enums.NotesStatus;
import com.animevault.enums.ReadingFormat;
import com.animevault.enums.ReadingStatus;
import com.animevault.enums.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class WorkRequestDTO {

    @Data
    public static class NewWork{
        @NotNull(message = "title is required")
        @Schema(description = "Title of the work")
        private String title;

        @NotNull(message = "animeStatus is required")
        @Schema(description = "Current anime status")
        private AnimeStatus animeStatus;

        @NotNull(message = "readingFormat is required")
        @Schema(description = "Format of the reading material")
        private ReadingFormat readingFormat;

        @NotNull(message = "readingStatus is required")
        @Schema(description = "Current reading status")
        private ReadingStatus readingStatus;

        @NotNull(message = "userStatus is required")
        @Schema(description = "Current user reading status")
        private UserStatus userStatus;

        @NotNull(message = "notesStatus is required")
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

        @Schema(description = "Current user reading status")
        private UserStatus userStatus;

        @Schema(description = "Notes or remarks status")
        private NotesStatus notesStatus;
    }
}