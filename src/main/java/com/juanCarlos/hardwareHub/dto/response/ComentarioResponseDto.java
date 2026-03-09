package com.juanCarlos.hardwareHub.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioResponseDto {

    private Long id;
    private String textoContenido;
    private Integer likes;
    private LocalDateTime fecha;
    private Long usuarioId;
    private Long comentarioId;
    private Long publicacionId;
    private Long publicacionMontajeId;
}
