package com.juanCarlos.hardwareHub.entity;

import com.juanCarlos.hardwareHub.entity.enums.CajaFormato;
import com.juanCarlos.hardwareHub.entity.enums.CajaPlacasBaseCompatibles;
import com.juanCarlos.hardwareHub.entity.enums.CajaTipoPsuCompatible;
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
@Table(name = "caja")
public class CajaEntity {

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

    @Null
    @Column(name = "precio", precision = 7, scale = 2)
    private BigDecimal precio;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "formato")
    private CajaFormato formato;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "placas_base_compatibles")
    private CajaPlacasBaseCompatibles placasBaseCompatibles;

    @Null
    @Column(name = "color")
    private String color;

    @NonNull
    @JdbcTypeCode(value = SqlTypes.JSON)
    @Column(name = "dimensiones")
    private Map<String, Object> dimensiones;

    @Null
    @Enumerated(value = EnumType.STRING)
    @Column(name = "psu_compatible")
    private CajaTipoPsuCompatible psuCompatible;

    @Null
    @JdbcTypeCode(value = SqlTypes.JSON)
    @Column(name = "espacio_max_gpu")
    private Map<String, Object> espacioMaxGpu;

    @Null
    @Column(name = "bahias_25")
    private Integer bahias25;

    @Null
    @Column(name = "bahias_35")
    private Integer bahias35;

    @Null
    @JdbcTypeCode(value = SqlTypes.JSON)
    @Column(name = "espacio_ventiladores")
    private Map<String, Object> espacioVentiladores;

    @Null
    @Column(name = "ventiladores_incluidos")
    private Boolean ventiladoresIncluidos;

    @Null
    @JdbcTypeCode(value = SqlTypes.JSON)
    @Column(name = "soportes_radiador")
    private Map<String, Object> soportesRadiador;

    @Null
    @JdbcTypeCode(value = SqlTypes.JSON)
    @Column(name = "conectividad_frontal")
    private Map<String, Object> conectividadFrontal;

    @Null
    @Column(name = "rgb")
    private Boolean rgb;

    @Null
    @Column(name = "altura_max_enfriador_cpu")
    private Integer alturaMaxEnfriadorCpu;
}
