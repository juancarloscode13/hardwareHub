package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.FabricanteRequestDto;
import com.juanCarlos.hardwareHub.dto.response.FabricanteResponseDto;
import com.juanCarlos.hardwareHub.service.FabricanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fabricantes")
@AllArgsConstructor
@Tag(name = "Fabricante", description = "Gestión de fabricantes de componentes hardware")
public class FabricanteController {

    private final FabricanteService fabricanteService;

    @GetMapping
    @Operation(summary = "Listar todos los fabricantes con paginación y filtros opcionales")
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
    @Operation(summary = "Obtener un fabricante por su ID")
    public ResponseEntity<FabricanteResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(fabricanteService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo fabricante")
    public ResponseEntity<FabricanteResponseDto> create(@Valid @RequestBody FabricanteRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fabricanteService.create(requestDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un fabricante existente por su ID")
    public ResponseEntity<FabricanteResponseDto> update(@PathVariable Long id, @Valid @RequestBody FabricanteRequestDto requestDto) {
        return ResponseEntity.ok(fabricanteService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un fabricante por su ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        fabricanteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
