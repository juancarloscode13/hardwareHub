package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.PublicacionRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PublicacionResponseDto;
import com.juanCarlos.hardwareHub.service.PublicacionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
@AllArgsConstructor
public class PublicacionController {

    private final PublicacionService publicacionService;

    @GetMapping
    public ResponseEntity<List<PublicacionResponseDto>> getAll() {
        return ResponseEntity.ok(publicacionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(publicacionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PublicacionResponseDto> create(@Valid @RequestBody PublicacionRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(publicacionService.create(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionResponseDto> update(@PathVariable Long id, @Valid @RequestBody PublicacionRequestDto requestDto) {
        return ResponseEntity.ok(publicacionService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        publicacionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/fecha/desc")
    public ResponseEntity<List<PublicacionResponseDto>> getAllOrderByFechaDesc() {
        return ResponseEntity.ok(publicacionService.getAllOrderByFechaDesc());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PublicacionResponseDto>> getByUsuarioId(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(publicacionService.getByUsuarioId(usuarioId));
    }
}
