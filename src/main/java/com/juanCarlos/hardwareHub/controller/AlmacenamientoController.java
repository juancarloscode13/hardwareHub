package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.AlmacenamientoRequestDto;
import com.juanCarlos.hardwareHub.dto.response.AlmacenamientoResponseDto;
import com.juanCarlos.hardwareHub.service.AlmacenamientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/almacenamientos")
@AllArgsConstructor
@Tag(name = "Almacenamiento", description = "Gestión de dispositivos de almacenamiento (SSD, HDD, NVMe)")
public class AlmacenamientoController {

    private final AlmacenamientoService almacenamientoService;

    @GetMapping
    @Operation(summary = "Listar todos los almacenamientos con paginación y filtros opcionales")
    public ResponseEntity<Page<AlmacenamientoResponseDto>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(almacenamientoService.searchAll(filter, page, size, sort));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un almacenamiento por su ID")
    public ResponseEntity<AlmacenamientoResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(almacenamientoService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo dispositivo de almacenamiento")
    public ResponseEntity<AlmacenamientoResponseDto> create(@Valid @RequestBody AlmacenamientoRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(almacenamientoService.create(requestDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un almacenamiento existente por su ID")
    public ResponseEntity<AlmacenamientoResponseDto> update(@PathVariable Long id, @Valid @RequestBody AlmacenamientoRequestDto requestDto) {
        return ResponseEntity.ok(almacenamientoService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un almacenamiento por su ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        almacenamientoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
