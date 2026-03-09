package com.juanCarlos.hardwareHub.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicacionResponseDto {

    private Long id;
    private String contenidoTexto;
    private byte[] multimedia;
    private Long montajeId;
    private Integer likes;
    private LocalDateTime fecha;
    private Long usuarioId;
}
