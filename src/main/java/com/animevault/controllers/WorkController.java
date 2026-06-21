package com.animevault.controllers;


import com.animevault.dto.ApiResponseDTO;
import com.animevault.dto.WorkRequestDTO;
import com.animevault.services.WorkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/works")
@Tag(name = "Works")
public class WorkController {

    @Autowired
    private WorkService workService;

    @GetMapping("/search")
    @Operation(summary = "Fetch the complete AniMeVault list",
            description = "Returns the full AniMeVault collection.")
    public ResponseEntity<ApiResponseDTO> searchWorks(){
        return workService.searchWorks();
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new work",
            description = "Add a new work into the AniMeVault collection.")
    public ResponseEntity<ApiResponseDTO> registerWork(
            @RequestBody WorkRequestDTO.NewWork newWork) {
        return workService.registerWork(newWork);
    }

}
