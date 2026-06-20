package com.animevault.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Standard API response")
public class ApiResponseDTO {

    @Schema(description = "HTTP status code of the operation")
    private int status;

    @Schema(description = "Response message")
    private String message;

    @Schema(description = "Additional response data")
    private Object data;

    @Schema(description = "Timestamp of the response")
    private LocalDateTime timestamp = LocalDateTime.now();
}
