package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.PlacaBaseRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PlacaBaseResponseDto;
import com.juanCarlos.hardwareHub.service.PlacaBaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/placas-base")
@AllArgsConstructor
@Tag(name = "Placa Base", description = "Gestión de placas base")
public class PlacaBaseController {

    private final PlacaBaseService placaBaseService;

    @GetMapping
    @Operation(summary = "Listar todas las placas base con paginación y filtros opcionales")
    public ResponseEntity<Page<PlacaBaseResponseDto>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(placaBaseService.searchAll(filter, page, size, sort));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una placa base por su ID")
    public ResponseEntity<PlacaBaseResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(placaBaseService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva placa base")
    public ResponseEntity<PlacaBaseResponseDto> create(@Valid @RequestBody PlacaBaseRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(placaBaseService.create(requestDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una placa base existente por su ID")
    public ResponseEntity<PlacaBaseResponseDto> update(@PathVariable Long id, @Valid @RequestBody PlacaBaseRequestDto requestDto) {
        return ResponseEntity.ok(placaBaseService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una placa base por su ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        placaBaseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
