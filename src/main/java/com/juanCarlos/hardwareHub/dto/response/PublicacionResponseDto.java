package com.juanCarlos.hardwareHub.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de respuesta de una publicación")
public class PublicacionResponseDto {

    @Schema(description = "Identificador único", example = "1")
    private Long id;
    @Schema(description = "Texto de la publicación", example = "Mi nuevo montaje gaming con Ryzen 9950X y RTX 5090!")
    private String contenidoTexto;
    @Schema(description = "Contenido multimedia en bytes (imagen/vídeo)")
    private byte[] multimedia;
    @Schema(description = "ID del montaje asociado a la publicación", example = "1")
    private Long montajeId;
    @Schema(description = "Número de likes", example = "42")
    private Integer likes;
    @Schema(description = "Fecha y hora de creación de la publicación", example = "2026-03-14T10:30:00")
    private LocalDateTime fecha;
    @Schema(description = "ID del usuario autor de la publicación", example = "1")
    private Long usuarioId;
}
