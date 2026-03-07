package com.juanCarlos.hardwareHub.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicacionMontajeResponseDto {

    private Long id;
    private String contenidoTexto;
    private byte[] multimedia;
    private Long montajeId;
    private Long usuarioId;
}
