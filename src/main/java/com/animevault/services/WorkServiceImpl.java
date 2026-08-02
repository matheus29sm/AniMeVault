package com.animevault.services;

import com.animevault.dto.ApiResponseDTO;
import com.animevault.dto.WorkRequestDTO;
import com.animevault.dto.WorkResponseDTO;
import com.animevault.exception.ServiceException;
import com.animevault.repository.WorkRepository;
import com.animevault.utils.PagedUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@Service
public class WorkServiceImpl implements WorkService{

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private PagedUtil pagedUtil;

    @Override
    public ResponseEntity<ApiResponseDTO> searchWorks(Long rank,
                                                      String title,
                                                      boolean isActive,
                                                      Integer page,
                                                      Integer size) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("rank").ascending());

        Page<WorkResponseDTO.Work> response =
                workRepository.searchWorks(
                        rank,
                        title,
                        isActive,
                        pageable);

        return ResponseEntity.status(OK).body(
                new ApiResponseDTO(OK.value(),
                "List retrieved successfully",
                        pagedUtil.fromPage(response),
                LocalDateTime.now()));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> registerWork(WorkRequestDTO.NewWork newWork) {
        workRepository.registerWork(
                newWork.getTitle(),
                newWork.getAnimeStatus().name(),
                newWork.getReadingFormat().name(),
                newWork.getReadingStatus().name(),
                newWork.getUserStatus().name(),
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

        Pageable pageable = PageRequest.of(
                0,
                10,
                Sort.by("rank").ascending());

        Page<WorkResponseDTO.Work> listWork =
                workRepository.searchWorks(rank, title, true, pageable);

        if (listWork.isEmpty()) {
            throw new ServiceException(NOT_FOUND,
                    "Work not found or is currently inactive with the provided parameters.");
        }

        WorkResponseDTO.Work work = listWork.stream().toList().get(0);

        if (updateWork.getAnimeStatus() == null) {
            updateWork.setAnimeStatus(work.getAnimeStatus());
        }
        if (updateWork.getReadingFormat() == null) {
            updateWork.setReadingFormat(work.getReadingFormat());
        }
        if (updateWork.getReadingStatus() == null) {
            updateWork.setReadingStatus(work.getReadingStatus());
        }
        if (updateWork.getUserStatus() == null){
            updateWork.setUserStatus(work.getUserStatus());
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
                updateWork.getUserStatus().name(),
                updateWork.getNotesStatus().name());

        return ResponseEntity.status(OK).body(
                new ApiResponseDTO(OK.value(),
                        "Work successfully updated in AniMeVault",
                        null,
                        LocalDateTime.now()));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> deactivateWork(Long rank) {
        Pageable pageable = PageRequest.of(
                0,
                10,
                Sort.by("rank").ascending());

        Page<WorkResponseDTO.Work> listWork =
                workRepository.searchWorks(rank, null, true, pageable);

        if (listWork.isEmpty()) {
            throw new ServiceException(CONFLICT,
                    "Work not found or is already inactive in AniMeVault.");
        }

        workRepository.deactivateWork(rank);

        return ResponseEntity.status(OK).body(
                new ApiResponseDTO(OK.value(),
                        "Work successfully deactivated in AniMeVault",
                        null,
                        LocalDateTime.now()));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> activateWork(Long rank) {

        Pageable pageable = PageRequest.of(
                0,
                10,
                Sort.by("rank").ascending());

        Page<WorkResponseDTO.Work> listWork =
                workRepository.searchWorks(rank, null, false, pageable);

        if (listWork.isEmpty()) {
            throw new ServiceException(CONFLICT,
                    "Work not found or is already active in AniMeVault.");
        }

        workRepository.activateWork(rank);

        return ResponseEntity.status(OK).body(
                new ApiResponseDTO(OK.value(),
                        "Work successfully activated in AniMeVault",
                        null,
                        LocalDateTime.now()));
    }

}
