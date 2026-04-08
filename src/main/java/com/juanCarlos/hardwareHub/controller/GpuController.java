package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.GpuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.GpuResponseDto;
import com.juanCarlos.hardwareHub.service.GpuService;
import com.juanCarlos.hardwareHub.service.implementation.GpuServiceImplementation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para entidad GpuEntity
 *
 * @see GpuServiceImplementation
 * @author Juan Carlos
 */
@RestController
@RequestMapping("/api/gpus")
@AllArgsConstructor
@Tag(name = "GPU", description = "Gestión de tarjetas gráficas (GPU)")
public class GpuController {

    private final GpuService gpuService;

    @GetMapping
    @Operation(summary = "Listar todas las GPUs con paginación y filtros opcionales")
    public ResponseEntity<Page<GpuResponseDto>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(gpuService.searchAll(filter, page, size, sort));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una GPU por su ID")
    public ResponseEntity<GpuResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(gpuService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva GPU")
    public ResponseEntity<GpuResponseDto> create(@Valid @RequestBody GpuRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gpuService.create(requestDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una GPU existente por su ID")
    public ResponseEntity<GpuResponseDto> update(@PathVariable Long id, @Valid @RequestBody GpuRequestDto requestDto) {
        return ResponseEntity.ok(gpuService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una GPU por su ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        gpuService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
