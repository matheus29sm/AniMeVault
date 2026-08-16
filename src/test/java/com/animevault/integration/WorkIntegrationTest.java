package com.animevault.integration;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    class SearchWorksSuccess {

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

    @Nested
    @DisplayName("Register work")
    class RegisterWork {

        @Test
        @DisplayName("Should register work successfully")
        void shouldRegisterWorkSuccessfully() throws Exception {
            String request = """
                {
                    "title": "TESTE INTEGRAT",
                    "animeStatus": "ONGOING",
                    "readingFormat": "MANGA",
                    "readingStatus": "ONGOING",
                    "userStatus": "READING",
                    "notesStatus": "READING"
                }""";

            mockMvc.perform(post("/works/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.status")
                            .value(201))
                    .andExpect(jsonPath("$.message")
                            .value("Work successfully registered into AniMeVault"));
        }

        @Test
        @DisplayName("Should return bad request when request body is empty")
        void shouldReturnBadRequestWhenRequestBodyIsEmpty() throws Exception {
            mockMvc.perform(post("/works/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(""))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.status").value(400))
                    .andExpect(jsonPath("$.message")
                            .value("The request body is invalid or empty"));
        }

        @Test
        @DisplayName("Should return bad request when request body is malformed")
        void shouldReturnBadRequestWhenRequestBodyIsMalformed() throws Exception {
            String malformedRequest = """
                {
                    "title": "TESTE INTEGRAT",
                    "animeStatus":
                """;

            mockMvc.perform(post("/works/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(malformedRequest))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.status")
                            .value(400))
                    .andExpect(jsonPath("$.message")
                            .value("The request body is invalid or empty"));
        }

        @ParameterizedTest(name = "[{index}] Should return bad request when {1} is missing")
        @MethodSource("invalidRegisterRequests")
        @DisplayName("Should return bad request when a required field is missing")
        void shouldReturnBadRequestWhenFieldIsMissing(String requestBody, String missingField) throws Exception {
            mockMvc.perform(post("/works/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.status")
                            .value(400))
                    .andExpect(jsonPath("$.message")
                            .value(String.format("%s is required", missingField)));
        }

        static Stream<Arguments> invalidRegisterRequests() {
            return Stream.of(
                    Arguments.of("""
                        {
                            "title": null,
                            "animeStatus": "ONGOING",
                            "readingFormat": "MANGA",
                            "readingStatus": "ONGOING",
                            "userStatus": "READING",
                            "notesStatus": "READING"
                        }""", "title"),
                    Arguments.of("""
                        {
                            "title": "TESTE INTEGRAT",
                            "animeStatus": null,
                            "readingFormat": "MANGA",
                            "readingStatus": "ONGOING",
                            "userStatus": "READING",
                            "notesStatus": "READING"
                        }""", "animeStatus"),
                    Arguments.of("""
                        {
                            "title": "TESTE INTEGRAT",
                            "animeStatus": "ONGOING",
                            "readingFormat": null,
                            "readingStatus": "ONGOING",
                            "userStatus": "READING",
                            "notesStatus": "READING"
                        }""", "readingFormat"),
                    Arguments.of("""
                        {
                            "title": "TESTE INTEGRAT",
                            "animeStatus": "ONGOING",
                            "readingFormat": "MANGA",
                            "readingStatus": null,
                            "userStatus": "READING",
                            "notesStatus": "READING"
                        }""", "readingStatus"),
                    Arguments.of("""
                        {
                            "title": "TESTE INTEGRAT",
                            "animeStatus": "ONGOING",
                            "readingFormat": "MANGA",
                            "readingStatus": "ONGOING",
                            "userStatus": null,
                            "notesStatus": "READING"
                        }""", "userStatus"),
                    Arguments.of("""
                        {
                            "title": "TESTE INTEGRAT",
                            "animeStatus": "ONGOING",
                            "readingFormat": "MANGA",
                            "readingStatus": "ONGOING",
                            "userStatus": "READING",
                            "notesStatus": null
                        }""", "notesStatus")
            );
        }

    }

}
