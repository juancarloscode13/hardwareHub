package com.juanCarlos.hardwareHub.dto.response;

import com.juanCarlos.hardwareHub.entity.enums.PlacaBaseChipset;
import com.juanCarlos.hardwareHub.entity.enums.PlacaBaseFormato;
import com.juanCarlos.hardwareHub.entity.enums.PlacaBaseRamSoportada;
import com.juanCarlos.hardwareHub.entity.enums.PlacaBaseSocketCompatible;
import com.juanCarlos.hardwareHub.entity.enums.PlacaBaseWifiSoportado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlacaBaseResponseDto {

    private Long id;
    private String modelo;
    private Long fabricanteId;
    private BigDecimal precio;
    private PlacaBaseSocketCompatible socketCompatible;
    private PlacaBaseChipset chipset;
    private Integer memoriaMaxima;
    private Integer espaciosRam;
    private PlacaBaseRamSoportada tipoRamSoportada;
    private PlacaBaseFormato formato;
    private Integer ranurasExpansion;
    private Integer ranurasAlmacenamiento;
    private Integer puertosUsb;
    private Map<String, Object> conectividadInterna;
    private PlacaBaseWifiSoportado wifiSoportado;
}
