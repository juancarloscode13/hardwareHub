package com.juanCarlos.hardwareHub.entity;

import com.juanCarlos.hardwareHub.entity.enums.RamTipo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ram")
public class RamEntity {

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

    @Null
    @Column(name = "velocidad")
    private Integer velocidad;

    @Null
    @Column(name = "cantidad")
    private Integer cantidad;

    @Null
    @Column(name = "modulos")
    private Integer modulos;

    @Null
    @Column(name = "capacidad_por_modulo")
    private Integer capacidadPorModulo;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "tipo")
    private RamTipo tipo;

    @Null
    @Column(name = "latencia")
    private Integer latencia;
}
