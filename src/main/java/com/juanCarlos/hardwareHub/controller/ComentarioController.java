package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.ComentarioRequestDto;
import com.juanCarlos.hardwareHub.dto.response.ComentarioResponseDto;
import com.juanCarlos.hardwareHub.service.ComentarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comentarios")
@AllArgsConstructor
public class ComentarioController {

    private final ComentarioService comentarioService;

    @GetMapping
    public ResponseEntity<Page<ComentarioResponseDto>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(comentarioService.searchAll(filter, page, size, sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(comentarioService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ComentarioResponseDto> create(@Valid @RequestBody ComentarioRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioService.create(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioResponseDto> update(@PathVariable Long id, @Valid @RequestBody ComentarioRequestDto requestDto) {
        return ResponseEntity.ok(comentarioService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        comentarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
