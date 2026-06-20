package com.animevault.services;

import com.animevault.dto.ApiResponseDTO;
import org.springframework.http.ResponseEntity;

public interface WorkService {

    ResponseEntity<ApiResponseDTO> searchWorks();
}
