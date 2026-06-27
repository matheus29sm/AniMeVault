package com.animevault.services;

import com.animevault.dto.ApiResponseDTO;
import com.animevault.dto.WorkRequestDTO;
import com.animevault.dto.WorkResponseDTO;
import com.animevault.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Service
public class WorkServiceImpl implements WorkService{

    @Autowired
    private WorkRepository workRepository;

    @Override
    public ResponseEntity<ApiResponseDTO> searchWorks() {
        List<WorkResponseDTO.Work> response = workRepository.searchWorks();
        List<WorkResponseDTO.Work> response =
                workRepository.searchWorks(null, null);

        return ResponseEntity.status(OK).body(
                new ApiResponseDTO(OK.value(),
                "List retrieved successfully",
                response,
                LocalDateTime.now()));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> registerWork(WorkRequestDTO.NewWork newWork) {
        workRepository.registerWork(
                newWork.getTitle(),
                newWork.getAnimeStatus().name(),
                newWork.getReadingFormat().name(),
                newWork.getReadingStatus().name(),
                newWork.getNotesStatus().name()
        );

        return ResponseEntity.status(CREATED).body(
                new ApiResponseDTO(CREATED.value(),
                "Work successfully registered into AniMeVault",
                null,
                LocalDateTime.now()));
    }

}
