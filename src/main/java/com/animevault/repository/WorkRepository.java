package com.animevault.repository;

import com.animevault.dto.WorkResponseDTO;
import com.animevault.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import static com.animevault.repository.WorkRepositoryImpl.SEARCH_WORKS;

public interface WorkRepository extends JpaRepository<Work, Long> {

    @Query(value = SEARCH_WORKS, nativeQuery = true)
    List<WorkResponseDTO.Work> searchWorks();
}

