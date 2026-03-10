package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.RefrigeracionRequestDto;
import com.juanCarlos.hardwareHub.dto.response.RefrigeracionResponseDto;
import com.juanCarlos.hardwareHub.service.RefrigeracionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/refrigeraciones")
@AllArgsConstructor
public class RefrigeracionController {

    private final RefrigeracionService refrigeracionService;

    @GetMapping
    public ResponseEntity<Page<RefrigeracionResponseDto>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(refrigeracionService.searchAll(filter, page, size, sort));
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
}
