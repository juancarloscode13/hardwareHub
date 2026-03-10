package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.PsuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PsuResponseDto;
import com.juanCarlos.hardwareHub.service.PsuService;
import org.springframework.data.domain.Page;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/psus")
@AllArgsConstructor
public class PsuController {

    private final PsuService psuService;

    @GetMapping
    public ResponseEntity<Page<PsuResponseDto>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(psuService.searchAll(filter, page, size, sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PsuResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(psuService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PsuResponseDto> create(@Valid @RequestBody PsuRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(psuService.create(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PsuResponseDto> update(@PathVariable Long id, @Valid @RequestBody PsuRequestDto requestDto) {
        return ResponseEntity.ok(psuService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        psuService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    
}
