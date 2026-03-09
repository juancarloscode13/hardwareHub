package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.GpuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.GpuResponseDto;
import com.juanCarlos.hardwareHub.service.GpuService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gpus")
@AllArgsConstructor
public class GpuController {

    private final GpuService gpuService;

    @GetMapping
    public ResponseEntity<List<GpuResponseDto>> getAll() {
        return ResponseEntity.ok(gpuService.getAll());
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

    @GetMapping("/pcie/{conectividadPcie}")
    public ResponseEntity<List<GpuResponseDto>> getByConectividadPcie(@PathVariable Integer conectividadPcie) {
        return ResponseEntity.ok(gpuService.getByConectividadPcie(conectividadPcie));
    }

    @GetMapping("/alto/max/{altoGpu}")
    public ResponseEntity<List<GpuResponseDto>> getByAltoGpuLessThanEqual(@PathVariable Integer altoGpu) {
        return ResponseEntity.ok(gpuService.getByAltoGpuLessThanEqual(altoGpu));
    }

    @GetMapping("/passmark/min/{puntuacionPassmark}")
    public ResponseEntity<List<GpuResponseDto>> getByPuntuacionPassmarkGreaterThanEqual(@PathVariable Integer puntuacionPassmark) {
        return ResponseEntity.ok(gpuService.getByPuntuacionPassmarkGreaterThanEqual(puntuacionPassmark));
    }

    @GetMapping("/passmark/ranking")
    public ResponseEntity<List<GpuResponseDto>> getAllOrderByPuntuacionPassmarkDesc() {
        return ResponseEntity.ok(gpuService.getAllOrderByPuntuacionPassmarkDesc());
    }
}
