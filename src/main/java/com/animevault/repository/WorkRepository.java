package com.animevault.repository;

import com.animevault.dto.WorkResponseDTO;
import com.animevault.entity.Work;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import static com.animevault.repository.WorkRepositoryImpl.ACTIVATE_WORK;
import static com.animevault.repository.WorkRepositoryImpl.DEACTIVATE_WORK;
import static com.animevault.repository.WorkRepositoryImpl.REGISTER_NEW_WORK;
import static com.animevault.repository.WorkRepositoryImpl.SEARCH_WORKS;
import static com.animevault.repository.WorkRepositoryImpl.UPDATE_WORK;

public interface WorkRepository extends JpaRepository<Work, Long> {

    @Query(value = SEARCH_WORKS, nativeQuery = true)
    List<WorkResponseDTO.Work> searchWorks(
            Long rank,
            String title);

    @Transactional
    @Modifying
    @Query(value = REGISTER_NEW_WORK, nativeQuery = true)
    int registerWork(
            String title,
            String animeStatus,
            String readingFormat,
            String readingStatus,
            String notesStatus
    );

    @Transactional
    @Modifying
    @Query(value = UPDATE_WORK, nativeQuery = true)
    int updateWork(
            Long rank,
            String title,
            String animeStatus,
            String readingFormat,
            String readingStatus,
            String notesStatus);

    @Transactional
    @Modifying
    @Query(value = DEACTIVATE_WORK, nativeQuery = true)
    int deactivateWork(Long rank);

    @Transactional
    @Modifying
    @Query(value = ACTIVATE_WORK, nativeQuery = true)
    int activateWork(Long rank);
}

