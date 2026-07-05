package com.animevault.controllers;


import com.animevault.dto.ApiResponseDTO;
import com.animevault.dto.WorkRequestDTO;
import com.animevault.services.WorkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<ApiResponseDTO> searchWorks(
            @Parameter(description = "Rank of the work") @RequestParam(required = false) Long rank,
            @Parameter(description = "Title of the work") @RequestParam(required = false) String title,
            @Parameter(description = "") @RequestParam boolean isActive,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ){
        return workService.searchWorks(rank, title, isActive, page, size);
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new work",
            description = "Add a new work into the AniMeVault collection.")
    public ResponseEntity<ApiResponseDTO> registerWork(
            @RequestBody WorkRequestDTO.NewWork newWork) {
        return workService.registerWork(newWork);
    }

    @PutMapping("/update")
    @Operation(summary = "Update work",
            description = "Update an existing work in AniMeVault.")
    public ResponseEntity<ApiResponseDTO> updateWork(
            @Parameter(description = "Rank of the work") @RequestParam(required = false) Long rank,
            @Parameter(description = "Title of the work") @RequestParam(required = false) String title,
            @RequestBody WorkRequestDTO.UpdateWork updateWork) {
        return workService.updateWork(rank, title, updateWork);
    }

    @DeleteMapping("/deactivate")
    @Operation(summary = "Deactivate work",
            description = "Soft delete: mark work as inactive in AniMeVault.")
    public ResponseEntity<ApiResponseDTO> deactivateWork(
            @Parameter(description = "Rank of the work") @RequestParam Long rank) {
        return workService.deactivateWork(rank);
    }

    @PutMapping("/activate")
    @Operation(summary = "Activate work",
            description = "Restore: mark work as active in AniMeVault.")
    public ResponseEntity<ApiResponseDTO> activateWork(
            @Parameter(description = "Rank of the work") @RequestParam Long rank) {

        return workService.activateWork(rank);
    }

}
