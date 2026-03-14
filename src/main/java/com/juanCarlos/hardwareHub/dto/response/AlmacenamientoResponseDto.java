package com.juanCarlos.hardwareHub.dto.response;

import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoConectividad;
import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoFormato;
import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoTipo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de respuesta de un dispositivo de almacenamiento")
public class AlmacenamientoResponseDto {

    @Schema(description = "Identificador único", example = "1")
    private Long id;
    @Schema(description = "Nombre del modelo", example = "Samsung 990 Pro")
    private String modelo;
    @Schema(description = "ID del fabricante", example = "4")
    private Long fabricanteId;
    @Schema(description = "Precio en euros", example = "89.99")
    private BigDecimal precio;
    @Schema(description = "Capacidad en GB", example = "2000")
    private BigDecimal capacidad;
    @Schema(description = "Tipo de almacenamiento", example = "SSD")
    private AlmacenamientoTipo tipo;
    @Schema(description = "Factor de forma", example = "M2")
    private AlmacenamientoFormato formato;
    @Schema(description = "Velocidad de lectura secuencial en MB/s", example = "7450")
    private Integer velocidadLectura;
    @Schema(description = "Velocidad de escritura secuencial en MB/s", example = "6900")
    private Integer velocidadEscritura;
    @Schema(description = "Interfaz de conectividad", example = "NVME")
    private AlmacenamientoConectividad conectividad;
}
