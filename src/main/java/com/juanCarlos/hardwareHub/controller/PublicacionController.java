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
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        log.info("Buscando publicaciones: filter={}, page={}, size={}, sort={}", filter, page, size, sort);
        Page<PublicacionResponseDto> result = publicacionService.searchAll(filter, page, size, sort);
        log.info("Búsqueda completada: {} publicaciones encontradas", result.getTotalElements());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una publicación por su ID")
    public ResponseEntity<PublicacionResponseDto> getById(@PathVariable Long id) {
        log.info("Obteniendo publicación con id={}", id);
        PublicacionResponseDto result = publicacionService.getById(id);
        log.info("Publicación obtenida exitosamente: id={}", id);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva publicación")
    public ResponseEntity<PublicacionResponseDto> create(@Valid @RequestBody PublicacionRequestDto requestDto) {
        log.info("Creando nueva publicación para usuarioId={}", requestDto.getUsuarioId());
        PublicacionResponseDto result = publicacionService.create(requestDto);
        log.info("Publicación creada exitosamente: id={}, usuarioId={}", result.getId(), requestDto.getUsuarioId());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una publicación existente por su ID")
    public ResponseEntity<PublicacionResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody PublicacionRequestDto requestDto
    ) {
        log.info("Actualizando publicación: id={}", id);
        PublicacionResponseDto result = publicacionService.update(id, requestDto);
        log.info("Publicación actualizada exitosamente: id={}", id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una publicación por su ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Eliminando publicación: id={}", id);
        publicacionService.deleteById(id);
        log.info("Publicación eliminada exitosamente: id={}", id);
        return ResponseEntity.noContent().build();
    }

    //Endpoints de reacciones

    @PostMapping("/{id}/reaccion")
    @Operation(summary = "Añadir o actualizar la reacción del usuario sobre la publicación")
    public ResponseEntity<ReaccionConteoDto> addOrUpdateReaction(
            @PathVariable Long id,
            @Valid @RequestBody ReaccionRequestDto requestDto
    ) {
        log.info("Procesando reacción: publicacionId={}, usuarioId={}, tipo={}", id, requestDto.getUsuarioId(), requestDto.getTipo());
        ReaccionConteoDto result = reaccionService.addOrUpdateReaction(id, requestDto.getUsuarioId(), requestDto.getTipo());
        log.info("Reacción procesada exitosamente: publicacionId={}", id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}/reaccion/{usuarioId}")
    @Operation(summary = "Eliminar la reacción del usuario sobre la publicación")
    public ResponseEntity<Void> removeReaction(
            @PathVariable Long id,
            @PathVariable Long usuarioId
    ) {
        log.info("Eliminando reacción: publicacionId={}, usuarioId={}", id, usuarioId);
        reaccionService.removeReaction(id, usuarioId);
        log.info("Reacción eliminada exitosamente: publicacionId={}, usuarioId={}", id, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/reacciones")
    @Operation(summary = "Obtener el conteo de reacciones agrupadas por tipo de una publicación")
    public ResponseEntity<ReaccionConteoDto> getReacciones(@PathVariable Long id) {
        log.info("Obteniendo conteo de reacciones: publicacionId={}", id);
        ReaccionConteoDto result = reaccionService.getReactionsByPublication(id);
        log.info("Conteo obtenido exitosamente: publicacionId={}", id);
        return ResponseEntity.ok(result);
    }
}
