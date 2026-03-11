package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.CajaRequestDto;
import com.juanCarlos.hardwareHub.dto.response.CajaResponseDto;
import com.juanCarlos.hardwareHub.service.CajaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cajas")
@AllArgsConstructor
public class CajaController {

    private final CajaService cajaService;

    @GetMapping
    public ResponseEntity<Page<CajaResponseDto>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(cajaService.searchAll(filter, page, size, sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CajaResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cajaService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CajaResponseDto> create(@Valid @RequestBody CajaRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cajaService.create(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CajaResponseDto> update(@PathVariable Long id, @Valid @RequestBody CajaRequestDto requestDto) {
        return ResponseEntity.ok(cajaService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        cajaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
