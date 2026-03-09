package com.juanCarlos.hardwareHub.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicacionRequestDto {

    private String contenidoTexto;
    private byte[] multimedia;
    private Integer likes;
    private Long usuarioId;
}
