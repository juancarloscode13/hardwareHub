package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.FabricanteRequestDto;
import com.juanCarlos.hardwareHub.dto.response.FabricanteResponseDto;
import com.juanCarlos.hardwareHub.service.FabricanteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fabricantes")
@AllArgsConstructor
public class FabricanteController {

    private final FabricanteService fabricanteService;

    @GetMapping
    public ResponseEntity<Page<FabricanteResponseDto>> searchAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort
    ) {
        Page<FabricanteResponseDto> response = fabricanteService.searchAll(filter, page, size, sort);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FabricanteResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(fabricanteService.getById(id));
    }

    @PostMapping
    public ResponseEntity<FabricanteResponseDto> create(@Valid @RequestBody FabricanteRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fabricanteService.create(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FabricanteResponseDto> update(@PathVariable Long id, @Valid @RequestBody FabricanteRequestDto requestDto) {
        return ResponseEntity.ok(fabricanteService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        fabricanteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
