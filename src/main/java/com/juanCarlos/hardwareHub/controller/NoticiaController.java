package com.juanCarlos.hardwareHub.controller;

import com.juanCarlos.hardwareHub.dto.response.NoticiaResponseDto;
import com.juanCarlos.hardwareHub.service.NoticiaService;
import com.juanCarlos.hardwareHub.service.implementation.NoticiaServiceImplementation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador para noticias externas (GNews)
 *
 * @see NoticiaServiceImplementation
 * @author Juan Carlos
 */
@RestController
@RequestMapping("/api/noticias")
@AllArgsConstructor
@Tag(name = "Noticias", description = "Noticias tecnológicas desde GNews")
public class NoticiaController {

    private final NoticiaService noticiaService;

    @GetMapping
    @Operation(summary = "Obtener titulares tecnológicos de la última semana")
    public ResponseEntity<List<NoticiaResponseDto>> getNoticias() {
        return ResponseEntity.ok(noticiaService.getNoticias());
    }
}

