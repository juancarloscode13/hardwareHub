package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.CpuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.CpuResponseDto;
import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
import com.juanCarlos.hardwareHub.service.CpuService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<List<CpuResponseDto>> getAll() {
        return ResponseEntity.ok(cpuService.getAll());
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

    @GetMapping("/socket/{cpuSocket}")
    public ResponseEntity<List<CpuResponseDto>> getByCpuSocket(@PathVariable CpuSocket cpuSocket) {
        return ResponseEntity.ok(cpuService.getByCpuSocket(cpuSocket));
    }

    @GetMapping("/pcie/{conectividadPcie}")
    public ResponseEntity<List<CpuResponseDto>> getByConectividadPcie(@PathVariable Integer conectividadPcie) {
        return ResponseEntity.ok(cpuService.getByConectividadPcie(conectividadPcie));
    }

    @GetMapping("/passmark/min/{puntuacionPassmark}")
    public ResponseEntity<List<CpuResponseDto>> getByPuntuacionPassmarkGreaterThanEqual(@PathVariable Integer puntuacionPassmark) {
        return ResponseEntity.ok(cpuService.getByPuntuacionPassmarkGreaterThanEqual(puntuacionPassmark));
    }

    @GetMapping("/passmark/ranking")
    public ResponseEntity<List<CpuResponseDto>> getAllOrderByPuntuacionPassmarkDesc() {
        return ResponseEntity.ok(cpuService.getAllOrderByPuntuacionPassmarkDesc());
    }
}
