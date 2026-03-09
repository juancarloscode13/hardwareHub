package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.AlmacenamientoRequestDto;
import com.juanCarlos.hardwareHub.dto.response.AlmacenamientoResponseDto;
import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoConectividad;
import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoFormato;
import com.juanCarlos.hardwareHub.service.AlmacenamientoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/almacenamientos")
@AllArgsConstructor
public class AlmacenamientoController {

    private final AlmacenamientoService almacenamientoService;

    @GetMapping
    public ResponseEntity<List<AlmacenamientoResponseDto>> getAll() {
        return ResponseEntity.ok(almacenamientoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlmacenamientoResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(almacenamientoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<AlmacenamientoResponseDto> create(@Valid @RequestBody AlmacenamientoRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(almacenamientoService.create(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlmacenamientoResponseDto> update(@PathVariable Long id, @Valid @RequestBody AlmacenamientoRequestDto requestDto) {
        return ResponseEntity.ok(almacenamientoService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        almacenamientoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/conectividad/{conectividad}")
    public ResponseEntity<List<AlmacenamientoResponseDto>> getByConectividad(@PathVariable AlmacenamientoConectividad conectividad) {
        return ResponseEntity.ok(almacenamientoService.getByConectividad(conectividad));
    }

    @GetMapping("/formato/{formato}")
    public ResponseEntity<List<AlmacenamientoResponseDto>> getByFormato(@PathVariable AlmacenamientoFormato formato) {
        return ResponseEntity.ok(almacenamientoService.getByFormato(formato));
    }
}
