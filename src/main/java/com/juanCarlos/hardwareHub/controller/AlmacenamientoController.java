package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.AlmacenamientoRequestDto;
import com.juanCarlos.hardwareHub.dto.response.AlmacenamientoResponseDto;
import com.juanCarlos.hardwareHub.service.AlmacenamientoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/almacenamientos")
@AllArgsConstructor
public class AlmacenamientoController {

    private final AlmacenamientoService almacenamientoService;

    @GetMapping
    public ResponseEntity<Page<AlmacenamientoResponseDto>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(almacenamientoService.searchAll(filter, page, size, sort));
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
}
