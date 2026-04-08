package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.MontajeRequestDto;
import com.juanCarlos.hardwareHub.dto.response.MontajeResponseDto;
import com.juanCarlos.hardwareHub.service.MontajeService;
import com.juanCarlos.hardwareHub.service.implementation.MontajeServiceImplementation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para entidad MontajeEntity
 *
 * @see MontajeServiceImplementation
 * @author Juan Carlos
 */
@RestController
@RequestMapping("/api/montajes")
@AllArgsConstructor
@Tag(name = "Montaje", description = "Gestión de montajes (builds) de PC completos")
public class MontajeController {

    private final MontajeService montajeService;

    @GetMapping
    @Operation(summary = "Listar todos los montajes con paginación y filtros opcionales")
    public ResponseEntity<Page<MontajeResponseDto>> searchAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort
    ) {
        Page<MontajeResponseDto> response = montajeService.searchAll(filter, page, size, sort);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un montaje por su ID")
    public ResponseEntity<MontajeResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(montajeService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo montaje de PC")
    public ResponseEntity<MontajeResponseDto> create(@Valid @RequestBody MontajeRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(montajeService.create(requestDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un montaje existente por su ID")
    public ResponseEntity<MontajeResponseDto> update(@PathVariable Long id, @Valid @RequestBody MontajeRequestDto requestDto) {
        return ResponseEntity.ok(montajeService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un montaje por su ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        montajeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
