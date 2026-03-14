package com.juanCarlos.hardwareHub.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de entrada para crear o actualizar un comentario")
public class ComentarioRequestDto {

    @Schema(description = "Texto del comentario", example = "¡Qué build tan increíble!")
    private String textoContenido;
    @Schema(description = "Número de likes del comentario", example = "0")
    private Integer likes;
    @Schema(description = "ID del usuario autor del comentario", example = "1")
    private Long usuarioId;
    @Schema(description = "ID del comentario padre si es una respuesta (null si es raíz)", example = "null")
    private Long comentarioId;
    @Schema(description = "ID de la publicación a la que pertenece el comentario", example = "5")
    private Long publicacionId;
}
