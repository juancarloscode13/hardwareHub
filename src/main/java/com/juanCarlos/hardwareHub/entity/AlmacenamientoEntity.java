package com.juanCarlos.hardwareHub.entity;

import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoConectividad;
import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoFormato;
import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoTipo;
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
@Table(name = "almacenamiento")
public class AlmacenamientoEntity {

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
    @Column(name = "capacidad", precision = 4, scale = 3)
    private BigDecimal capacidad;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "tipo")
    private AlmacenamientoTipo tipo;

    @Null
    @Enumerated(value = EnumType.STRING)
    @Column(name = "formato")
    private AlmacenamientoFormato formato;

    @NonNull
    @Column(name = "velocidad_lectura")
    private Integer velocidadLectura;

    @NonNull
    @Column(name = "velocidad_escritura")
    private Integer velocidadEscritura;

    @Null
    @Enumerated(value = EnumType.STRING)
    @Column(name = "conectividad")
    private AlmacenamientoConectividad conectividad;
}
