package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.ComentarioRequestDto;
import com.juanCarlos.hardwareHub.dto.response.ComentarioResponseDto;
import com.juanCarlos.hardwareHub.service.ComentarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
@AllArgsConstructor
public class ComentarioController {

    private final ComentarioService comentarioService;

    @GetMapping
    public ResponseEntity<List<ComentarioResponseDto>> getAll() {
        return ResponseEntity.ok(comentarioService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(comentarioService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ComentarioResponseDto> create(@Valid @RequestBody ComentarioRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioService.create(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioResponseDto> update(@PathVariable Long id, @Valid @RequestBody ComentarioRequestDto requestDto) {
        return ResponseEntity.ok(comentarioService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        comentarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/fecha/desc")
    public ResponseEntity<List<ComentarioResponseDto>> getAllOrderByFechaDesc() {
        return ResponseEntity.ok(comentarioService.getAllOrderByFechaDesc());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ComentarioResponseDto>> getByUsuarioId(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(comentarioService.getByUsuarioId(usuarioId));
    }

    @GetMapping("/publicacion/{publicacionId}")
    public ResponseEntity<List<ComentarioResponseDto>> getByPublicacionId(@PathVariable Long publicacionId) {
        return ResponseEntity.ok(comentarioService.getByPublicacionId(publicacionId));
    }

    @GetMapping("/comentario/{comentarioId}")
    public ResponseEntity<List<ComentarioResponseDto>> getByComentarioId(@PathVariable Long comentarioId) {
        return ResponseEntity.ok(comentarioService.getByComentarioId(comentarioId));
    }
}
