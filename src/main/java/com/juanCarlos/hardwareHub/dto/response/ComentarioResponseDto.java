package com.juanCarlos.hardwareHub.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de respuesta de un comentario")
public class ComentarioResponseDto {

    @Schema(description = "Identificador único", example = "1")
    private Long id;
    @Schema(description = "Texto del comentario", example = "¡Qué build tan increíble!")
    private String textoContenido;
    @Schema(description = "Número de likes del comentario", example = "5")
    private Integer likes;
    @Schema(description = "Fecha y hora de creación del comentario", example = "2026-03-14T12:00:00")
    private LocalDateTime fecha;
    @Schema(description = "ID del usuario autor del comentario", example = "1")
    private Long usuarioId;
    @Schema(description = "ID del comentario padre si es una respuesta (null si es raíz)", example = "null")
    private Long comentarioId;
    @Schema(description = "ID de la publicación a la que pertenece el comentario", example = "5")
    private Long publicacionId;
}
