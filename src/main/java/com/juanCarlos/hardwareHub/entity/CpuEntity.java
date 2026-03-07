package com.juanCarlos.hardwareHub.entity;

import com.juanCarlos.hardwareHub.entity.enums.CpuArquitectura;
import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
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
@Table(name = "cpu")
public class CpuEntity {

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
    @Column(name = "cpu_socket")
    private CpuSocket cpuSocket;

    @NonNull
    @JdbcTypeCode(value = SqlTypes.JSON)
    @Column(name = "cores")
    private Map<String, Object> cores;

    @Null
    @Column(name = "cache_apilada")
    private Boolean cacheApilada;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "arquitectura")
    private CpuArquitectura arquitectura;

    @Null
    @Column(name = "precio", precision = 7, scale = 2)
    private BigDecimal precio;

    @Null
    @Column(name = "hilos")
    private Integer hilos;

    @Null
    @Column(name = "hyperthreading")
    private Boolean hyperthreading;

    @Null
    @Column(name = "frecuencia_max", precision = 5, scale = 3)
    private BigDecimal frecuenciaMax;

    @Null
    @Column(name = "frecuencia_min", precision = 5, scale = 3)
    private BigDecimal frecuenciaMin;

    @Null
    @Column(name = "cantidad_cache")
    private Integer cantidadCache;

    @Null
    @Column(name = "tdp")
    private Integer tdp;

    @Null
    @Column(name = "temperatura_max")
    private Integer temperaturaMax;

    @Null
    @Column(name = "conectividad_pcie")
    private Integer conectividadPcie;

    @Null
    @Column(name = "graficos_integrados")
    private String graficosIntegrados;
}
