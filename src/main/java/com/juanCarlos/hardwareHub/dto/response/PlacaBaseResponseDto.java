package com.juanCarlos.hardwareHub.dto.response;

import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
import com.juanCarlos.hardwareHub.entity.enums.PlacaBaseChipset;
import com.juanCarlos.hardwareHub.entity.enums.PlacaBaseFormato;
import com.juanCarlos.hardwareHub.entity.enums.PlacaBaseWifiSoportado;
import com.juanCarlos.hardwareHub.entity.enums.RamTipo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de respuesta de una placa base")
public class PlacaBaseResponseDto {

    @Schema(description = "Identificador único", example = "1")
    private Long id;
    @Schema(description = "Nombre del modelo", example = "ASUS ROG STRIX X870-E")
    private String modelo;
    @Schema(description = "ID del fabricante", example = "7")
    private Long fabricanteId;
    @Schema(description = "Precio en euros", example = "449.99")
    private BigDecimal precio;
    @Schema(description = "Socket de CPU compatible", example = "AM5")
    private CpuSocket socketCompatible;
    @Schema(description = "Chipset de la placa base", example = "X870E")
    private PlacaBaseChipset chipset;
    @Schema(description = "Memoria máxima soportada en GB", example = "256")
    private Integer memoriaMaxima;
    @Schema(description = "Número de slots de RAM", example = "4")
    private Integer espaciosRam;
    @Schema(description = "Tipo de RAM soportada", example = "DDR5")
    private RamTipo tipoRamSoportada;
    @Schema(description = "Factor de forma de la placa base", example = "ATX")
    private PlacaBaseFormato formato;
    @Schema(description = "Número de ranuras de expansión PCIe", example = "3")
    private Integer ranurasExpansion;
    @Schema(description = "Número de slots M.2 para almacenamiento", example = "4")
    private Integer ranurasAlmacenamiento;
    @Schema(description = "Número de puertos USB traseros", example = "8")
    private Integer puertosUsb;
    @Schema(description = "Conectividad interna (USB headers, ARGB, etc.)")
    private Map<String, Object> conectividadInterna;
    @Schema(description = "Estándar WiFi soportado", example = "WIFI7")
    private PlacaBaseWifiSoportado wifiSoportado;
}
