package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.UsuarioRequestDto;
import com.juanCarlos.hardwareHub.dto.response.UsuarioResponseDto;
import com.juanCarlos.hardwareHub.service.UsuarioService;
import com.juanCarlos.hardwareHub.service.implementation.UsuarioServiceImplementation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
        return ResponseEntity.ok(usuarioService.searchAll(filter, page, size, sort));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un usuario por su ID")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario")
    public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.create(requestDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario existente por su ID")
    public ResponseEntity<UsuarioResponseDto> update(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDto requestDto) {
        return ResponseEntity.ok(usuarioService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario por su ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //Sistema de seguidores

    @PostMapping("/{id}/follow/{targetId}")
    @Operation(summary = "El usuario {id} sigue al usuario {targetId}")
    public ResponseEntity<UsuarioResponseDto> followUser(
            @PathVariable Long id,
            @PathVariable Long targetId
    ) {
        return ResponseEntity.ok(usuarioService.followUser(id, targetId));
    }

    @DeleteMapping("/{id}/follow/{targetId}")
    @Operation(summary = "El usuario {id} deja de seguir al usuario {targetId}")
    public ResponseEntity<UsuarioResponseDto> unfollowUser(
            @PathVariable Long id,
            @PathVariable Long targetId
    ) {
        return ResponseEntity.ok(usuarioService.unfollowUser(id, targetId));
    }

    @GetMapping("/{id}/followers")
    @Operation(summary = "Obtener la lista de seguidores del usuario {id}")
    public ResponseEntity<List<UsuarioResponseDto>> getFollowers(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.getFollowers(id));
    }

    @GetMapping("/{id}/following")
    @Operation(summary = "Obtener la lista de usuarios a los que sigue el usuario {id}")
    public ResponseEntity<List<UsuarioResponseDto>> getFollowing(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.getFollowing(id));
    }
}
