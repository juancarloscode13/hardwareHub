package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.ComentarioRequestDto;
import com.juanCarlos.hardwareHub.dto.response.ComentarioResponseDto;
import com.juanCarlos.hardwareHub.service.ComentarioService;
import com.juanCarlos.hardwareHub.service.implementation.ComentarioServiceImplementation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para entidad ComentarioEntity
 *
 * @see ComentarioServiceImplementation
 * @author Juan Carlos
 */
@RestController
@RequestMapping("/api/comentarios")
@AllArgsConstructor
@Tag(name = "Comentario", description = "Gestión de comentarios en publicaciones")
public class ComentarioController {

    private final ComentarioService comentarioService;

    @GetMapping
    @Operation(summary = "Listar todos los comentarios con paginación y filtros opcionales")
    public ResponseEntity<Page<ComentarioResponseDto>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(comentarioService.searchAll(filter, page, size, sort));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un comentario por su ID")
    public ResponseEntity<ComentarioResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(comentarioService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo comentario")
    public ResponseEntity<ComentarioResponseDto> create(@Valid @RequestBody ComentarioRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioService.create(requestDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un comentario existente por su ID")
    public ResponseEntity<ComentarioResponseDto> update(@PathVariable Long id, @Valid @RequestBody ComentarioRequestDto requestDto) {
        return ResponseEntity.ok(comentarioService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un comentario por su ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        comentarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
