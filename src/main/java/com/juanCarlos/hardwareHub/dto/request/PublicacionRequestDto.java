package com.juanCarlos.hardwareHub.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de entrada para crear o actualizar una publicación")
public class PublicacionRequestDto {

    @Schema(description = "Texto de la publicación", example = "Mi nuevo montaje gaming con Ryzen 9950X y RTX 5090!")
    private String contenidoTexto;
    @Schema(description = "Contenido multimedia en bytes (imagen/vídeo)")
    private byte[] multimedia;
    @Schema(description = "ID del montaje asociado a la publicación", example = "1")
    private Long montajeId;
    @Schema(description = "ID del usuario autor de la publicación", example = "1")
    private Long usuarioId;
}
