package com.animevault.services;

import com.animevault.dto.ApiResponseDTO;
import com.animevault.dto.WorkRequestDTO;
import com.animevault.dto.WorkResponseDTO;
import com.animevault.exception.ServiceException;
import com.animevault.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@Service
public class WorkServiceImpl implements WorkService{

    @Autowired
    private WorkRepository workRepository;

    @Override
    public ResponseEntity<ApiResponseDTO> searchWorks(Long rank,
                                                      String title,
                                                      boolean isActive) {
        List<WorkResponseDTO.Work> response =
                workRepository.searchWorks(
                        rank,
                        title,
                        isActive);

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

    @Override
    public ResponseEntity<ApiResponseDTO> updateWork(Long rank,
                                                     String title,
                                                     WorkRequestDTO.UpdateWork updateWork) {
        if (rank == null && title == null){
            throw new ServiceException(BAD_REQUEST,
                    "Either rank or title must be provided to update a work.");
        }

        List<WorkResponseDTO.Work> listWork =
                workRepository.searchWorks(rank, title, true);

        if (listWork.isEmpty()) {
            throw new ServiceException(NOT_FOUND,
                    "Work not found or is currently inactive with the provided parameters.");
        }

        WorkResponseDTO.Work work = listWork.get(0);

        if (updateWork.getAnimeStatus() == null) {
            updateWork.setAnimeStatus(work.getAnimeStatus());
        }
        if (updateWork.getReadingFormat() == null) {
            updateWork.setReadingFormat(work.getReadingFormat());
        }
        if (updateWork.getReadingStatus() == null) {
            updateWork.setReadingStatus(work.getReadingStatus());
        }
        if (updateWork.getNotesStatus() == null) {
            updateWork.setNotesStatus(work.getNotesStatus());
        }

        workRepository.updateWork(
                rank,
                title,
                updateWork.getAnimeStatus().name(),
                updateWork.getReadingFormat().name(),
                updateWork.getReadingStatus().name(),
                updateWork.getNotesStatus().name());

        return ResponseEntity.status(OK).body(
                new ApiResponseDTO(OK.value(),
                        "Work successfully updated in AniMeVault",
                        null,
                        LocalDateTime.now()));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> deactivateWork(Long rank) {

        workRepository.deactivateWork(rank);

        return ResponseEntity.status(OK).body(
                new ApiResponseDTO(OK.value(),
                        "Work successfully deactivated in AniMeVault",
                        null,
                        LocalDateTime.now()));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> activateWork(Long rank) {

        workRepository.activateWork(rank);

        return ResponseEntity.status(OK).body(
                new ApiResponseDTO(OK.value(),
                        "Work successfully activated in AniMeVault",
                        null,
                        LocalDateTime.now()));
    }

}
