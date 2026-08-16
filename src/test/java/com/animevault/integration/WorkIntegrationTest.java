package com.animevault.integration;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Testcontainers
class WorkIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:17-alpine");

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("Search works")
    class SearchWorks {

        @Test
        @DisplayName("Should return paged results when filters match")
        @Sql("/sql/works-active.sql")
        void shouldReturnPagedResultsWhenFiltersMatch() throws Exception {
            mockMvc.perform(get("/works/search")
                            .param("title", "TESTE INTEGRAT")
                            .param("animeStatus", "ONGOING")
                            .param("readingFormat", "MANGA")
                            .param("readingStatus", "ONGOING")
                            .param("userStatus", "READING")
                            .param("notesStatus", "READING")
                            .param("isActive", "true"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status")
                            .value(200))
                    .andExpect(jsonPath("$.data.content[0].title")
                            .value("TESTE INTEGRAT"))
                    .andExpect(jsonPath("$.data.content[0].animeStatus")
                            .value("ONGOING"))
                    .andExpect(jsonPath("$.data.content[0].readingFormat")
                            .value("MANGA"))
                    .andExpect(jsonPath("$.data.content[0].readingStatus")
                            .value("ONGOING"))
                    .andExpect(jsonPath("$.data.content[0].userStatus")
                            .value("READING"))
                    .andExpect(jsonPath("$.data.content[0].notesStatus")
                            .value("READING"))
                    .andExpect(jsonPath("$.data.content[0].isActive")
                            .value(true));
        }

        @Test
        @DisplayName("Should return paged results when filtering by inactive works")
        @Sql("/sql/works-inactive.sql")
        void shouldReturnPagedResultsWhenFilteringByInactiveWorks() throws Exception {
            mockMvc.perform(get("/works/search")
                            .param("title", "TESTE INTEGRAT INACTIVE")
                            .param("isActive", "false"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status")
                            .value(200))
                    .andExpect(jsonPath("$.data.content[0].title")
                            .value("TESTE INTEGRAT INACTIVE"))
                    .andExpect(jsonPath("$.data.content[0].isActive").
                            value(false));
        }

        @Test
        @DisplayName("Should return no content when no works match filters")
        void shouldReturnNoContentWhenNoWorksMatchFilters () throws Exception {
            mockMvc.perform(get("/works/search")
                            .param("title", "NO EXISTENT WORK")
                            .param("isActive", "true"))
                    .andExpect(status().isNoContent())
                    .andExpect(jsonPath("$.status")
                            .value(204));
        }

    }

}
