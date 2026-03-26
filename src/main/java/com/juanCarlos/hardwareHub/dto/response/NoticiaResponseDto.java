package com.juanCarlos.hardwareHub.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO PÚBLICO — lo que el front recibe al consultar /api/noticias.
 * Completamente desacoplado de GNews: si se cambia de proveedor, este contrato no cambia.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de una noticia tecnológica")
public class NoticiaResponseDto {

    @Schema(description = "Título de la noticia", example = "Razer presenta sus portátiles gaming Blade 16 para 2026")
    private String title;

    @Schema(description = "Descripción breve del artículo", example = "Razer anuncia el Blade 16 con Intel Core Ultra 9, RTX 5090 y hasta un 60% más de batería.")
    private String description;

    @Schema(description = "Enlace al artículo original", example = "https://hardzone.es/noticias/equipos/razer-blade-16-2026/")
    private String url;

    @Schema(description = "URL de la imagen de portada del artículo", example = "https://hardzone.es/app/uploads-hardzone.es/2026/03/Blade-16-1.jpg")
    private String image;

    @Schema(description = "Fecha y hora de publicación en formato ISO 8601", example = "2026-03-25T19:49:17Z")
    private String publishedAt;

    @Schema(description = "Nombre del medio que publicó la noticia", example = "HardZone")
    private String sourceName;
}

