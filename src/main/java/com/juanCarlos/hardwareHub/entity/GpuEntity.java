package com.juanCarlos.hardwareHub.entity;

import com.juanCarlos.hardwareHub.entity.enums.GpuArquitectura;
import com.juanCarlos.hardwareHub.entity.enums.GpuEnsambladora;
import com.juanCarlos.hardwareHub.entity.enums.GpuGeneracion;
import com.juanCarlos.hardwareHub.entity.enums.GpuTipoVram;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gpu")
public class GpuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "modelo")
    private String modelo;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_fabricante")
    private FabricanteEntity fabricante;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "ensambladora")
    private GpuEnsambladora ensambladora;

    @Null
    @JdbcTypeCode(value = SqlTypes.JSON)
    @Column(name = "nucleos")
    private Map<String, Object> nucleos;

    @Null
    @Column(name = "frecuencia_max", precision = 5, scale = 3)
    private BigDecimal frecuenciaMax;

    @Null
    @Column(name = "frecuencia_min", precision = 5, scale = 3)
    private BigDecimal frecuenciaMin;

    @Null
    @Column(name = "temperatura_max")
    private Integer temperaturaMax;

    @Null
    @Column(name = "cantidad_vram")
    private Integer cantidadVram;

    @Null
    @Enumerated(value = EnumType.STRING)
    @Column(name = "tipo_vram")
    private GpuTipoVram tipoVram;

    @Null
    @Column(name = "ancho_banda")
    private Integer anchoBanda;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "arquitectura")
    private GpuArquitectura arquitectura;

    @Null
    @Column(name = "tdp")
    private Integer tdp;

    @Null
    @Column(name = "conectividad_pcie")
    private Integer conectividadPcie;

    @Null
    @Column(name = "precio", precision = 7, scale = 2)
    private BigDecimal precio;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "generacion")
    private GpuGeneracion generacion;

    @Null
    @Column(name = "alto_gpu")
    private Integer altoGpu;

    @Null
    @Column(name = "puntuacion_passmark")
    private Integer puntuacionPassmark;
}
