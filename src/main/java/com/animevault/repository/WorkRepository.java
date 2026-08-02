package com.animevault.repository;

import com.animevault.dto.WorkResponseDTO;
import com.animevault.entity.Work;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static com.animevault.repository.WorkRepositoryImpl.ACTIVATE_WORK;
import static com.animevault.repository.WorkRepositoryImpl.DEACTIVATE_WORK;
import static com.animevault.repository.WorkRepositoryImpl.REGISTER_NEW_WORK;
import static com.animevault.repository.WorkRepositoryImpl.SEARCH_WORKS;
import static com.animevault.repository.WorkRepositoryImpl.UPDATE_WORK;

public interface WorkRepository extends JpaRepository<Work, Long> {

    @Query(value = SEARCH_WORKS, nativeQuery = true)
    Page<WorkResponseDTO.Work> searchWorks(
            @Param("rank") Long rank,
            @Param("title") String title,
            @Param("animeStatus") String animeStatus,
            @Param("readingFormat") String readingFormat,
            @Param("readingStatus") String readingStatus,
            @Param("userStatus") String userStatus,
            @Param("notesStatus") String notesStatus,
            @Param("isActive") boolean isActive,
            Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = REGISTER_NEW_WORK, nativeQuery = true)
    int registerWork(
            @Param("title") String title,
            @Param("animeStatus") String animeStatus,
            @Param("readingFormat") String readingFormat,
            @Param("readingStatus") String readingStatus,
            @Param("userStatus") String userStatus,
            @Param("notesStatus") String notesStatus
    );

    @Transactional
    @Modifying
    @Query(value = UPDATE_WORK, nativeQuery = true)
    int updateWork(
            @Param("rank") Long rank,
            @Param("title") String title,
            @Param("animeStatus") String animeStatus,
            @Param("readingFormat") String readingFormat,
            @Param("readingStatus") String readingStatus,
            @Param("userStatus") String userStatus,
            @Param("notesStatus") String notesStatus);

    @Transactional
    @Modifying
    @Query(value = DEACTIVATE_WORK, nativeQuery = true)
    int deactivateWork(@Param("rank") Long rank);

    @Transactional
    @Modifying
    @Query(value = ACTIVATE_WORK, nativeQuery = true)
    int activateWork(@Param("rank") Long rank);
}

