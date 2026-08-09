package com.animevault.unit.services;

import com.animevault.dto.ApiResponseDTO;
import com.animevault.dto.PagedResponse;
import com.animevault.dto.WorkRequestDTO;
import com.animevault.dto.WorkResponseDTO;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkServiceTest {

    @InjectMocks
    private WorkServiceImpl workService;

    @Mock
    private WorkRepository workRepository;

    @Mock
    private WorkResponseDTO.Work work;

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

}