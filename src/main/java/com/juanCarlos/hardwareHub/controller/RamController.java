package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.RamRequestDto;
import com.juanCarlos.hardwareHub.dto.response.RamResponseDto;
import com.juanCarlos.hardwareHub.service.RamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rams")
@AllArgsConstructor
@Tag(name = "RAM", description = "Gestión de módulos de memoria RAM")
public class RamController {

    private final RamService ramService;

    @GetMapping
    @Operation(summary = "Listar todas las RAMs con paginación y filtros opcionales")
    public ResponseEntity<Page<RamResponseDto>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(ramService.searchAll(filter, page, size, sort));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una RAM por su ID")
    public ResponseEntity<RamResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ramService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo módulo de RAM")
    public ResponseEntity<RamResponseDto> create(@Valid @RequestBody RamRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ramService.create(requestDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un módulo de RAM existente por su ID")
    public ResponseEntity<RamResponseDto> update(@PathVariable Long id, @Valid @RequestBody RamRequestDto requestDto) {
        return ResponseEntity.ok(ramService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un módulo de RAM por su ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        ramService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
