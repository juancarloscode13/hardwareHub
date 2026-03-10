package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.CpuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.CpuResponseDto;
import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
import com.juanCarlos.hardwareHub.service.CpuService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cpus")
@AllArgsConstructor
public class CpuController {

    private final CpuService cpuService;

    @GetMapping
    public ResponseEntity<Page<CpuResponseDto>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(cpuService.searchAll(filter, page, size, sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CpuResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cpuService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CpuResponseDto> create(@Valid @RequestBody CpuRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cpuService.create(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CpuResponseDto> update(@PathVariable Long id, @Valid @RequestBody CpuRequestDto requestDto) {
        return ResponseEntity.ok(cpuService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        cpuService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
