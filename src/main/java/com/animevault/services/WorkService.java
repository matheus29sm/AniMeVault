package com.animevault.services;

import com.animevault.dto.ApiResponseDTO;
import com.animevault.dto.WorkRequestDTO;
import org.springframework.http.ResponseEntity;

public interface WorkService {

    ResponseEntity<ApiResponseDTO> searchWorks(Long rank,
                                               String title,
                                               boolean isActive,
                                               Integer page,
                                               Integer size);

    ResponseEntity<ApiResponseDTO> registerWork(WorkRequestDTO.NewWork newWork);

    ResponseEntity<ApiResponseDTO> updateWork(Long rank,
                                              String title,
                                              WorkRequestDTO.UpdateWork updateWork);

    ResponseEntity<ApiResponseDTO> deactivateWork(Long rank);

    ResponseEntity<ApiResponseDTO> activateWork(Long rank);
}
