package com.juanCarlos.hardwareHub.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioRequestDto {

    private String textoContenido;
    private Integer likes;
    private Long usuarioId;
    private Long comentarioId;
    private Long publicacionId;
    private Long publicacionMontajeId;
}
