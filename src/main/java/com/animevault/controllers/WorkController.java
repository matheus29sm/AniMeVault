package com.animevault.controllers;


import com.animevault.dto.ApiResponseDTO;
import com.animevault.services.WorkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/works")
@Tag(name = "Works")
public class WorkController {

    @Autowired
    private WorkService workService;

    @GetMapping("/buscar")
    @Operation(summary = "Fetch the complete AniMeVault list",
            description = "Returns the full AniMeVault collection.")
    public ResponseEntity<ApiResponseDTO> searchWorks(){
        return workService.searchWorks();
    }

}
