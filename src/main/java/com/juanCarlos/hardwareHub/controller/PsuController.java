package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.PsuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PsuResponseDto;
import com.juanCarlos.hardwareHub.entity.enums.PsuFactorForma;
import com.juanCarlos.hardwareHub.service.PsuService;
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
    public ResponseEntity<List<PsuResponseDto>> getAll() {
        return ResponseEntity.ok(psuService.getAll());
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

    @GetMapping("/factor-forma/{factorForma}")
    public ResponseEntity<List<PsuResponseDto>> getByFactorForma(@PathVariable PsuFactorForma factorForma) {
        return ResponseEntity.ok(psuService.getByFactorForma(factorForma));
    }

    @GetMapping("/potencia/min/{potencia}")
    public ResponseEntity<List<PsuResponseDto>> getByPotenciaGreaterThanEqual(@PathVariable Integer potencia) {
        return ResponseEntity.ok(psuService.getByPotenciaGreaterThanEqual(potencia));
    }
}
