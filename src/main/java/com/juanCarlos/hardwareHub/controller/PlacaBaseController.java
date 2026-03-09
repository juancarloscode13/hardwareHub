package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.request.PlacaBaseRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PlacaBaseResponseDto;
import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
import com.juanCarlos.hardwareHub.entity.enums.PlacaBaseFormato;
import com.juanCarlos.hardwareHub.entity.enums.RamTipo;
import com.juanCarlos.hardwareHub.service.PlacaBaseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/placas-base")
@AllArgsConstructor
public class PlacaBaseController {

    private final PlacaBaseService placaBaseService;

    @GetMapping
    public ResponseEntity<List<PlacaBaseResponseDto>> getAll() {
        return ResponseEntity.ok(placaBaseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlacaBaseResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(placaBaseService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PlacaBaseResponseDto> create(@Valid @RequestBody PlacaBaseRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(placaBaseService.create(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlacaBaseResponseDto> update(@PathVariable Long id, @Valid @RequestBody PlacaBaseRequestDto requestDto) {
        return ResponseEntity.ok(placaBaseService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        placaBaseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/socket/{socketCompatible}")
    public ResponseEntity<List<PlacaBaseResponseDto>> getBySocketCompatible(@PathVariable CpuSocket socketCompatible) {
        return ResponseEntity.ok(placaBaseService.getBySocketCompatible(socketCompatible));
    }

    @GetMapping("/tipo-ram/{tipoRamSoportada}")
    public ResponseEntity<List<PlacaBaseResponseDto>> getByTipoRamSoportada(@PathVariable RamTipo tipoRamSoportada) {
        return ResponseEntity.ok(placaBaseService.getByTipoRamSoportada(tipoRamSoportada));
    }

    @GetMapping("/formato/{formato}")
    public ResponseEntity<List<PlacaBaseResponseDto>> getByFormato(@PathVariable PlacaBaseFormato formato) {
        return ResponseEntity.ok(placaBaseService.getByFormato(formato));
    }
}
