package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.MontajeRequestDto;
import com.juanCarlos.hardwareHub.dto.response.MontajeResponseDto;
import com.juanCarlos.hardwareHub.service.MontajeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<MontajeResponseDto>> searchAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort
    ) {
        Page<MontajeResponseDto> response = montajeService.searchAll(filter, page, size, sort);
        return ResponseEntity.ok(response);
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
}
