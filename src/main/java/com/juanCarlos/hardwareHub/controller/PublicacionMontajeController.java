package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.PublicacionMontajeRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PublicacionMontajeResponseDto;
import com.juanCarlos.hardwareHub.service.PublicacionMontajeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicaciones-montaje")
@AllArgsConstructor
public class PublicacionMontajeController {

    private final PublicacionMontajeService publicacionMontajeService;

    @GetMapping
    public ResponseEntity<List<PublicacionMontajeResponseDto>> getAll() {
        return ResponseEntity.ok(publicacionMontajeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionMontajeResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(publicacionMontajeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PublicacionMontajeResponseDto> create(@Valid @RequestBody PublicacionMontajeRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(publicacionMontajeService.create(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionMontajeResponseDto> update(@PathVariable Long id, @Valid @RequestBody PublicacionMontajeRequestDto requestDto) {
        return ResponseEntity.ok(publicacionMontajeService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        publicacionMontajeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/fecha/desc")
    public ResponseEntity<List<PublicacionMontajeResponseDto>> getAllOrderByFechaDesc() {
        return ResponseEntity.ok(publicacionMontajeService.getAllOrderByFechaDesc());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PublicacionMontajeResponseDto>> getByUsuarioId(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(publicacionMontajeService.getByUsuarioId(usuarioId));
    }
}
