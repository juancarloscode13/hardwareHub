package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.MontajeRequestDto;
import com.juanCarlos.hardwareHub.dto.response.MontajeResponseDto;
import com.juanCarlos.hardwareHub.service.MontajeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/montajes")
@AllArgsConstructor
public class MontajeController {

    private final MontajeService montajeService;

    @GetMapping
    public ResponseEntity<List<MontajeResponseDto>> getAll() {
        return ResponseEntity.ok(montajeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MontajeResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(montajeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<MontajeResponseDto> create(@Valid @RequestBody MontajeRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(montajeService.create(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MontajeResponseDto> update(@PathVariable Long id, @Valid @RequestBody MontajeRequestDto requestDto) {
        return ResponseEntity.ok(montajeService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        montajeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<MontajeResponseDto>> getByUsuarioId(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(montajeService.getByUsuarioId(usuarioId));
    }
}
