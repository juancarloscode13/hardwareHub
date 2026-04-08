package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.PublicacionRequestDto;
import com.juanCarlos.hardwareHub.dto.request.ReaccionRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PublicacionResponseDto;
import com.juanCarlos.hardwareHub.dto.response.ReaccionConteoDto;
import com.juanCarlos.hardwareHub.service.PublicacionService;
import com.juanCarlos.hardwareHub.service.ReaccionService;
import com.juanCarlos.hardwareHub.service.implementation.PublicacionServiceImplementation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para entidad PublicacionEntity
 *
 * @see PublicacionServiceImplementation
 * @author Juan Carlos
 */
@RestController
@RequestMapping("/api/publicaciones")
@AllArgsConstructor
@Tag(name = "Publicación", description = "Gestión de publicaciones de montajes en la plataforma")
public class PublicacionController {

    private final PublicacionService publicacionService;
    private final ReaccionService reaccionService;

    @GetMapping
    @Operation(summary = "Listar todas las publicaciones con paginación y filtros opcionales")
    public ResponseEntity<Page<PublicacionResponseDto>> searchAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(publicacionService.searchAll(filter, page, size, sort));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una publicación por su ID")
    public ResponseEntity<PublicacionResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(publicacionService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva publicación")
    public ResponseEntity<PublicacionResponseDto> create(@Valid @RequestBody PublicacionRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(publicacionService.create(requestDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una publicación existente por su ID")
    public ResponseEntity<PublicacionResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody PublicacionRequestDto requestDto
    ) {
        return ResponseEntity.ok(publicacionService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una publicación por su ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        publicacionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //Endpoints de reacciones

    @PostMapping("/{id}/reaccion")
    @Operation(summary = "Añadir o actualizar la reacción del usuario sobre la publicación")
    public ResponseEntity<ReaccionConteoDto> addOrUpdateReaction(
            @PathVariable Long id,
            @Valid @RequestBody ReaccionRequestDto requestDto
    ) {
        return ResponseEntity.ok(
                reaccionService.addOrUpdateReaction(id, requestDto.getUsuarioId(), requestDto.getTipo())
        );
    }

    @DeleteMapping("/{id}/reaccion/{usuarioId}")
    @Operation(summary = "Eliminar la reacción del usuario sobre la publicación")
    public ResponseEntity<Void> removeReaction(
            @PathVariable Long id,
            @PathVariable Long usuarioId
    ) {
        reaccionService.removeReaction(id, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/reacciones")
    @Operation(summary = "Obtener el conteo de reacciones agrupadas por tipo de una publicación")
    public ResponseEntity<ReaccionConteoDto> getReacciones(@PathVariable Long id) {
        return ResponseEntity.ok(reaccionService.getReactionsByPublication(id));
    }
}
