package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.RefrigeracionRequestDto;
import com.juanCarlos.hardwareHub.dto.response.RefrigeracionResponseDto;
import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
import com.juanCarlos.hardwareHub.service.RefrigeracionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/refrigeraciones")
@AllArgsConstructor
public class RefrigeracionController {

    private final RefrigeracionService refrigeracionService;

    @GetMapping
    public ResponseEntity<List<RefrigeracionResponseDto>> getAll() {
        return ResponseEntity.ok(refrigeracionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefrigeracionResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(refrigeracionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<RefrigeracionResponseDto> create(@Valid @RequestBody RefrigeracionRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(refrigeracionService.create(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RefrigeracionResponseDto> update(@PathVariable Long id, @Valid @RequestBody RefrigeracionRequestDto requestDto) {
        return ResponseEntity.ok(refrigeracionService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        refrigeracionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/socket/{socketCompatible}")
    public ResponseEntity<List<RefrigeracionResponseDto>> getBySocketCompatible(@PathVariable CpuSocket socketCompatible) {
        return ResponseEntity.ok(refrigeracionService.getBySocketCompatible(socketCompatible));
    }
}
