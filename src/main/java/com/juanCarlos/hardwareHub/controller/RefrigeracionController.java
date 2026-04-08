package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.RefrigeracionRequestDto;
import com.juanCarlos.hardwareHub.dto.response.RefrigeracionResponseDto;
import com.juanCarlos.hardwareHub.service.RefrigeracionService;
import com.juanCarlos.hardwareHub.service.implementation.RefrigeracionServiceImplementation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para entidad RefrigeracionEntity
 *
 * @see RefrigeracionServiceImplementation
 * @author Juan Carlos
 */
@RestController
@RequestMapping("/api/refrigeraciones")
@AllArgsConstructor
@Tag(name = "Refrigeración", description = "Gestión de sistemas de refrigeración para CPU")
public class RefrigeracionController {

    private final RefrigeracionService refrigeracionService;

    @GetMapping
    @Operation(summary = "Listar todas las refrigeraciones con paginación y filtros opcionales")
    public ResponseEntity<Page<RefrigeracionResponseDto>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(refrigeracionService.searchAll(filter, page, size, sort));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una refrigeración por su ID")
    public ResponseEntity<RefrigeracionResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(refrigeracionService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo sistema de refrigeración")
    public ResponseEntity<RefrigeracionResponseDto> create(@Valid @RequestBody RefrigeracionRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(refrigeracionService.create(requestDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una refrigeración existente por su ID")
    public ResponseEntity<RefrigeracionResponseDto> update(@PathVariable Long id, @Valid @RequestBody RefrigeracionRequestDto requestDto) {
        return ResponseEntity.ok(refrigeracionService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una refrigeración por su ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        refrigeracionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
