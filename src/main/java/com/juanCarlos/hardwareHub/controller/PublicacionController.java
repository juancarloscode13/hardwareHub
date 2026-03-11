package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.PublicacionRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PublicacionResponseDto;
import com.juanCarlos.hardwareHub.service.PublicacionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<PublicacionResponseDto>> searchAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort
    ) {
        Page<PublicacionResponseDto> response = publicacionService.searchAll(filter, page, size, sort);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionResponseDto> getById(@PathVariable Long id) {
        PublicacionResponseDto responseDto = publicacionService.getById(id);
        return ResponseEntity.ok(responseDto);
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
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        publicacionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // legacy endpoints removed; use paginated `GET /api/publicaciones?filter=...` instead
}
