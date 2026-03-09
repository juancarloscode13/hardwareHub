package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.CajaRequestDto;
import com.juanCarlos.hardwareHub.dto.response.CajaResponseDto;
import com.juanCarlos.hardwareHub.entity.enums.CajaFormato;
import com.juanCarlos.hardwareHub.entity.enums.PsuFactorForma;
import com.juanCarlos.hardwareHub.service.CajaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cajas")
@AllArgsConstructor
public class CajaController {

    private final CajaService cajaService;

    @GetMapping
    public ResponseEntity<List<CajaResponseDto>> getAll() {
        return ResponseEntity.ok(cajaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CajaResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cajaService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CajaResponseDto> create(@Valid @RequestBody CajaRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cajaService.create(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CajaResponseDto> update(@PathVariable Long id, @Valid @RequestBody CajaRequestDto requestDto) {
        return ResponseEntity.ok(cajaService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        cajaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/placas-base/{placasBaseCompatibles}")
    public ResponseEntity<List<CajaResponseDto>> getByPlacasBaseCompatibles(@PathVariable CajaFormato placasBaseCompatibles) {
        return ResponseEntity.ok(cajaService.getByPlacasBaseCompatibles(placasBaseCompatibles));
    }

    @GetMapping("/psu/{psuCompatible}")
    public ResponseEntity<List<CajaResponseDto>> getByPsuCompatible(@PathVariable PsuFactorForma psuCompatible) {
        return ResponseEntity.ok(cajaService.getByPsuCompatible(psuCompatible));
    }

    @GetMapping("/altura-enfriador/min/{alturaMaxEnfriadorCpu}")
    public ResponseEntity<List<CajaResponseDto>> getByAlturaMaxEnfriadorCpuGreaterThanEqual(@PathVariable Integer alturaMaxEnfriadorCpu) {
        return ResponseEntity.ok(cajaService.getByAlturaMaxEnfriadorCpuGreaterThanEqual(alturaMaxEnfriadorCpu));
    }
}
