package com.animevault.unit.services;

import com.animevault.dto.ApiResponseDTO;
import com.animevault.dto.PagedResponse;
import com.animevault.dto.WorkRequestDTO;
import com.animevault.dto.WorkResponseDTO;
import com.animevault.enums.AnimeStatus;
import com.animevault.enums.NotesStatus;
import com.animevault.enums.ReadingFormat;
import com.animevault.enums.ReadingStatus;
import com.animevault.enums.UserStatus;
import com.animevault.exception.ServiceException;
import com.animevault.repository.WorkRepository;
import com.animevault.services.WorkServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(MockitoExtension.class)
class WorkServiceTest {

    @InjectMocks
    private WorkServiceImpl workService;

    @Mock
    private WorkRepository workRepository;

    @Mock
    private WorkResponseDTO.Work work;

    @Mock
    private WorkRequestDTO.NewWork newWork;

    @Mock
    private WorkRequestDTO.UpdateWork updateWork;

    @Nested
    @DisplayName("Search works")
    class SearchWorks {

        @Test
        @DisplayName("Should return paged results for works successfully")
        void shouldReturnPagedResultsForWorksSuccessfully() {
            Long rank = 1L;
            String title = "One Piece";
            boolean isActive = true;
            Pageable pageable = PageRequest.of(0, 1,
                    Sort.by("rank").ascending());

            Page<WorkResponseDTO.Work> expectedPage = new PageImpl<>(List.of(work), pageable, 1);

            when(workRepository.searchWorks(rank, title, null,
                    null, null, null,
                    null, isActive, pageable))
                    .thenReturn(expectedPage);

            ResponseEntity<ApiResponseDTO> response = workService.searchWorks(
                    rank, title, null,
                    null, null, null,
                    null, isActive, 0, 1);

            PagedResponse<WorkResponseDTO.Work> actualPaged =
                    (PagedResponse<WorkResponseDTO.Work>) response.getBody().getData();

            verify(workRepository).searchWorks(rank, title, null,
                    null, null, null,
                    null, isActive, pageable);

            assertEquals(200, response.getBody().getStatus());
            assertTrue(response.getBody().getMessage().contains("List retrieved successfully"));
            assertEquals(1, actualPaged.getTotalElements());
            assertEquals(work, actualPaged.getContent().get(0));
        }

        @Test
        @DisplayName("Should return empty paged results when no works match.")
        void shouldReturnEmptyPagedResultsWhenNoWorksMatch() {
            Long rank = 0L;
            String title = "TESTE UNIT";
            boolean isActive = true;
            Pageable pageable = PageRequest.of(0, 1,
                    Sort.by("rank").ascending());

            when(workRepository.searchWorks(rank, title, null,
                    null, null, null,
                    null, isActive, pageable))
                    .thenReturn(new PageImpl<>(List.of()));

            ResponseEntity<ApiResponseDTO> response = workService.searchWorks(
                    rank, title, null,
                    null, null, null,
                    null, isActive, 0, 1);

            PagedResponse<WorkResponseDTO.Work> actualPaged =
                    (PagedResponse<WorkResponseDTO.Work>) response.getBody().getData();

            verify(workRepository).searchWorks(rank, title, null,
                    null, null, null,
                    null, isActive, pageable);

            assertEquals(204, response.getBody().getStatus());
            assertTrue(response.getBody().getMessage().contains("No works found for provided filters"));
            assertTrue(actualPaged.getContent().isEmpty());
        }

    }

    @Nested
    @DisplayName("Register work")
    class RegisterWork {

        @Test
        @DisplayName("Should register work successfully")
        void shouldRegisterWorkSuccessfully() {
            String title = "NewWork";

            when(newWork.getTitle()).thenReturn(title);
            when(newWork.getAnimeStatus()).thenReturn(AnimeStatus.ONGOING);
            when(newWork.getReadingFormat()).thenReturn(ReadingFormat.MANGA);
            when(newWork.getReadingStatus()).thenReturn(ReadingStatus.ONGOING);
            when(newWork.getUserStatus()).thenReturn(UserStatus.NOT_STARTED);
            when(newWork.getNotesStatus()).thenReturn(NotesStatus.NOT_READING);

            ResponseEntity<ApiResponseDTO> response = workService.registerWork(newWork);

            verify(workRepository).registerWork(
                    title,
                    AnimeStatus.ONGOING.name(),
                    ReadingFormat.MANGA.name(),
                    ReadingStatus.ONGOING.name(),
                    UserStatus.NOT_STARTED.name(),
                    NotesStatus.NOT_READING.name()
            );

            assertEquals(201, response.getBody().getStatus());
            assertTrue(response.getBody().getMessage().contains("Work successfully registered"));
        }

    }

    @Nested
    @DisplayName("Update work")
    class UpdateWork {

        private final Pageable PAGEABLE = PageRequest.of(0, 10,
                Sort.by("rank").ascending());
        private final boolean IS_ACTIVE = true;

        @Test
        @DisplayName("Should update work successfully")
        void shouldUpdateWorkSuccessfully() {
            Long rank = 1L;

            when(workRepository.searchWorks(rank, null, null,
                    null, null, null,
                    null, IS_ACTIVE, PAGEABLE))
                    .thenReturn(new PageImpl<>(List.of(work)));

            when(updateWork.getAnimeStatus()).thenReturn(AnimeStatus.ONGOING);
            when(updateWork.getReadingFormat()).thenReturn(ReadingFormat.MANGA);
            when(updateWork.getReadingStatus()).thenReturn(ReadingStatus.ONGOING);
            when(updateWork.getUserStatus()).thenReturn(UserStatus.NOT_STARTED);
            when(updateWork.getNotesStatus()).thenReturn(NotesStatus.NOT_READING);

            ResponseEntity<ApiResponseDTO> response = workService.updateWork(1L, null, updateWork);

            verify(workRepository).updateWork(
                    rank,
                    null,
                    AnimeStatus.ONGOING.name(),
                    ReadingFormat.MANGA.name(),
                    ReadingStatus.ONGOING.name(),
                    UserStatus.NOT_STARTED.name(),
                    NotesStatus.NOT_READING.name()
            );

            assertEquals(200, response.getBody().getStatus());
            assertTrue(response.getBody().getMessage().contains("Work successfully updated in AniMeVault"));
        }

        @Test
        @DisplayName("Should throw exception when neither rank nor title is provided")
        void shouldThrowExceptionWhenNeitherRankNorTitleIsProvided() {
            ServiceException exception = assertThrows(ServiceException.class,
                    () -> workService.updateWork(null, null, updateWork));

            assertEquals(BAD_REQUEST, exception.getStatus());
            assertEquals("Either rank or title must be provided to update a work.",
                    exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when work not found")
        void shouldThrowExceptionWhenWorkNotFound() {
            Long rank = 0L;

            when(workRepository.searchWorks(rank, null, null,
                    null, null, null,
                    null, IS_ACTIVE, PAGEABLE))
                    .thenReturn(new PageImpl<>(List.of()));

            ServiceException exception = assertThrows(ServiceException.class,
                    () -> workService.updateWork(rank, null, updateWork));

            assertEquals(NOT_FOUND, exception.getStatus());
            assertEquals("Work not found or is currently inactive with the provided parameters.",
                    exception.getMessage());
        }

    }

    @Nested
    @DisplayName("Deactivate work")
    class DeactivateWork {

        private final Pageable PAGEABLE = PageRequest.of(0, 10,
                Sort.by("rank").ascending());
        private final boolean IS_ACTIVE = true;

        @Test
        @DisplayName("Should deactivate work successfully")
        void shouldDeactivateWorkSuccessfully() {
            Long rank = 1L;

            when(workRepository.searchWorks(rank, null, null,
                    null, null, null,
                    null, IS_ACTIVE, PAGEABLE))
                    .thenReturn(new PageImpl<>(List.of(work)));

            ResponseEntity<ApiResponseDTO> response = workService.deactivateWork(rank);

            verify(workRepository).deactivateWork(rank);

            assertEquals(200, response.getBody().getStatus());
            assertTrue(response.getBody().getMessage().contains("Work successfully deactivated in AniMeVault"));
        }

        @Test
        @DisplayName("Should throw ConflictException when work already inactive")
        void shouldThrowConflictExceptionWhenWorkAlreadyInactive() {
            Long rank = 0L;

            when(workRepository.searchWorks(rank, null, null,
                    null, null, null,
                    null, IS_ACTIVE, PAGEABLE))
                    .thenReturn(new PageImpl<>(List.of()));

            ServiceException exception = assertThrows(ServiceException.class,
                    () -> workService.deactivateWork(rank));

            assertEquals(CONFLICT, exception.getStatus());
            assertEquals("Work not found or is already inactive in AniMeVault.",
                    exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Activate work")
    class ActivateWork {


        private final Pageable PAGEABLE = PageRequest.of(0, 10,
                Sort.by("rank").ascending());
        private final boolean IS_ACTIVE = false;

        @Test
        @DisplayName("Should activate work successfully")
        void shouldActivateWorkSuccessfully() {
            Long rank = 1L;

            when(workRepository.searchWorks(rank, null, null,
                    null, null, null,
                    null, IS_ACTIVE, PAGEABLE))
                    .thenReturn(new PageImpl<>(List.of(work)));

            ResponseEntity<ApiResponseDTO> response = workService.activateWork(rank);

            verify(workRepository).activateWork(rank);

            assertEquals(200, response.getBody().getStatus());
            assertTrue(response.getBody().getMessage().contains("Work successfully activated in AniMeVault"));
        }

        @Test
        @DisplayName("Should throw ConflictException when work already active")
        void shouldThrowConflictExceptionWhenWorkAlreadyActive() {
            Long rank = 0L;

            when(workRepository.searchWorks(rank, null, null,
                    null, null, null,
                    null, IS_ACTIVE, PAGEABLE))
                    .thenReturn(new PageImpl<>(List.of()));

            ServiceException exception = assertThrows(ServiceException.class,
                    () -> workService.activateWork(rank));

            assertEquals(CONFLICT, exception.getStatus());
            assertEquals("Work not found or is already active in AniMeVault.",
                    exception.getMessage());
        }
    }

}