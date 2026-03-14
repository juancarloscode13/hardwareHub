package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.PsuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PsuResponseDto;
import com.juanCarlos.hardwareHub.service.PsuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/psus")
@AllArgsConstructor
@Tag(name = "PSU", description = "Gestión de fuentes de alimentación (PSU)")
public class PsuController {

    private final PsuService psuService;

    @GetMapping
    @Operation(summary = "Listar todas las PSUs con paginación y filtros opcionales")
    public ResponseEntity<Page<PsuResponseDto>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(psuService.searchAll(filter, page, size, sort));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una PSU por su ID")
    public ResponseEntity<PsuResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(psuService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva PSU")
    public ResponseEntity<PsuResponseDto> create(@Valid @RequestBody PsuRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(psuService.create(requestDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una PSU existente por su ID")
    public ResponseEntity<PsuResponseDto> update(@PathVariable Long id, @Valid @RequestBody PsuRequestDto requestDto) {
        return ResponseEntity.ok(psuService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una PSU por su ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        psuService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
