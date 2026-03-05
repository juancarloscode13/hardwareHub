package com.juanCarlos.hardwareHub.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GpuGeneracion {

    GPU_GENERACION_RTX_4000("RTX 4000"),
    GPU_GENERACION_RTX_5000("RTX 5000"),
    GPU_GENERACION_RX_7000("RX 7000"),
    GPU_GENERACION_RX_9000("RX 9000"),
    GPU_GENERACION_ARC_SERIE_A("Arc Serie A"),
    GPU_GENERACION_ARC_SERIE_B("Arc Serie B");

    private String desc;
}