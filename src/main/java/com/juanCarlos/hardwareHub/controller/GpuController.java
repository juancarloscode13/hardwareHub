package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.GpuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.GpuResponseDto;
import com.juanCarlos.hardwareHub.service.GpuService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gpus")
@AllArgsConstructor
public class GpuController {

    private final GpuService gpuService;

    @GetMapping
    public ResponseEntity<Page<GpuResponseDto>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(gpuService.searchAll(filter, page, size, sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GpuResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(gpuService.getById(id));
    }

    @PostMapping
    public ResponseEntity<GpuResponseDto> create(@Valid @RequestBody GpuRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gpuService.create(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GpuResponseDto> update(@PathVariable Long id, @Valid @RequestBody GpuRequestDto requestDto) {
        return ResponseEntity.ok(gpuService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        gpuService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
