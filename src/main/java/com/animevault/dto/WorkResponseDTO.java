package com.animevault.dto;

import com.animevault.enums.AnimeStatus;
import com.animevault.enums.NotesStatus;
import com.animevault.enums.ReadingFormat;
import com.animevault.enums.ReadingStatus;
import com.animevault.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;

public class WorkResponseDTO {

    @Schema(description = "Work response model used in API output")
    @JsonPropertyOrder({"rank", "title", "animeStatus", "readingFormat", "readingStatus", "userStatus", "notesStatus"})
    public interface Work {
        @Schema(description = "Ranking position of the work")
        Integer getRank();

        @Schema(description = "Title of the work")
        String getTitle();

        @Schema(description = "Current anime status")
        AnimeStatus getAnimeStatus();

        @Schema(description = "Format of the reading material")
        ReadingFormat getReadingFormat();

        @Schema(description = "Current reading status")
        ReadingStatus getReadingStatus();

        @Schema(description = "Current user reading status")
        UserStatus getUserStatus();

        @Schema(description = "Notes or remarks status")
        NotesStatus getNotesStatus();

        @Schema(description = "Indicates whether the work is active" +
                " (true = active, false = inactive)")
        boolean getIsActive();
    }

}
