package com.animevault.services;

import com.animevault.dto.ApiResponseDTO;
import com.animevault.dto.WorkRequestDTO;
import org.springframework.http.ResponseEntity;

public interface WorkService {

    ResponseEntity<ApiResponseDTO> searchWorks();

    ResponseEntity<ApiResponseDTO> registerWork(WorkRequestDTO.NewWork newWork);
}
