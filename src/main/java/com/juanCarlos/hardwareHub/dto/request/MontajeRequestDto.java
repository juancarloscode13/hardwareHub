package com.juanCarlos.hardwareHub.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MontajeRequestDto {

    private Long ramId;
    private Long cpuId;
    private Long gpuId;
    private Long refrigeracionId;
    private Long cajaId;
    private Long placaBaseId;
    private Long psuId;
    private Long almacenamientoId;
    private Long usuarioId;
}
