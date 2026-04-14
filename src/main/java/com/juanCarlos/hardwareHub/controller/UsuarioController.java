package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.UsuarioRequestDto;
import com.juanCarlos.hardwareHub.dto.response.UsuarioResponseDto;
import com.juanCarlos.hardwareHub.service.UsuarioService;
import com.juanCarlos.hardwareHub.service.implementation.UsuarioServiceImplementation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para entidad UsuarioEntity
 *
 * @see UsuarioServiceImplementation
 * @author Juan Carlos
 */
@RestController
@RequestMapping("/api/usuarios")
@AllArgsConstructor
@Slf4j
@Tag(name = "Usuario", description = "Gestión de usuarios de la plataforma")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Listar todos los usuarios con paginación y filtros opcionales")
    public ResponseEntity<Page<UsuarioResponseDto>> searchAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort
    ) {
        log.info("Buscando usuarios: filter={}, page={}, size={}, sort={}", filter, page, size, sort);
        Page<UsuarioResponseDto> result = usuarioService.searchAll(filter, page, size, sort);
        log.info("Búsqueda completada: {} usuarios encontrados", result.getTotalElements());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un usuario por su ID")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id) {
        log.info("Obteniendo usuario con id={}", id);
        UsuarioResponseDto result = usuarioService.getById(id);
        log.info("Usuario obtenido exitosamente: id={}", id);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario")
    public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioRequestDto requestDto) {
        log.info("Creando nuevo usuario: email={}", requestDto.getEmail());
        UsuarioResponseDto result = usuarioService.create(requestDto);
        log.info("Usuario creado exitosamente: id={}, email={}", result.getId(), requestDto.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario existente por su ID")
    public ResponseEntity<UsuarioResponseDto> update(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDto requestDto) {
        log.info("Actualizando usuario: id={}", id);
        UsuarioResponseDto result = usuarioService.update(id, requestDto);
        log.info("Usuario actualizado exitosamente: id={}", id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario por su ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("Eliminando usuario: id={}", id);
        usuarioService.deleteById(id);
        log.info("Usuario eliminado exitosamente: id={}", id);
        return ResponseEntity.noContent().build();
    }

    //Sistema de seguidores

    @PostMapping("/{id}/follow/{targetId}")
    @Operation(summary = "El usuario {id} sigue al usuario {targetId}")
    public ResponseEntity<UsuarioResponseDto> followUser(
            @PathVariable Long id,
            @PathVariable Long targetId
    ) {
        log.info("Usuario {} intenta seguir a usuario {}", id, targetId);
        UsuarioResponseDto result = usuarioService.followUser(id, targetId);
        log.info("Usuario {} ahora sigue a usuario {}", id, targetId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}/follow/{targetId}")
    @Operation(summary = "El usuario {id} deja de seguir al usuario {targetId}")
    public ResponseEntity<UsuarioResponseDto> unfollowUser(
            @PathVariable Long id,
            @PathVariable Long targetId
    ) {
        log.info("Usuario {} intenta dejar de seguir a usuario {}", id, targetId);
        UsuarioResponseDto result = usuarioService.unfollowUser(id, targetId);
        log.info("Usuario {} ya no sigue a usuario {}", id, targetId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/followers")
    @Operation(summary = "Obtener la lista de seguidores del usuario {id}")
    public ResponseEntity<List<UsuarioResponseDto>> getFollowers(@PathVariable Long id) {
        log.info("Obteniendo lista de seguidores para usuario: id={}", id);
        List<UsuarioResponseDto> result = usuarioService.getFollowers(id);
        log.info("Lista de seguidores obtenida: id={}, total={}", id, result.size());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/following")
    @Operation(summary = "Obtener la lista de usuarios a los que sigue el usuario {id}")
    public ResponseEntity<List<UsuarioResponseDto>> getFollowing(@PathVariable Long id) {
        log.info("Obteniendo lista de usuarios seguidos por usuario: id={}", id);
        List<UsuarioResponseDto> result = usuarioService.getFollowing(id);
        log.info("Lista de usuarios seguidos obtenida: id={}, total={}", id, result.size());
        return ResponseEntity.ok(result);
    }
}
