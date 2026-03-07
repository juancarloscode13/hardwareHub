package com.juanCarlos.hardwareHub.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicacionMontajeRequestDto {

    private String contenidoTexto;
    private byte[] multimedia;
    private Long montajeId;
    private Long usuarioId;
}
