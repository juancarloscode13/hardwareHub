package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.UsuarioRequestDto;
import com.juanCarlos.hardwareHub.dto.response.UsuarioResponseDto;
import com.juanCarlos.hardwareHub.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Page<UsuarioResponseDto> response = usuarioService.searchAll(filter, page, size, sort);
        return ResponseEntity.ok(response);
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
}
