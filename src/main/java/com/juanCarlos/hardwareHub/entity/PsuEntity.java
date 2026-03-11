package com.juanCarlos.hardwareHub.entity;

import com.juanCarlos.hardwareHub.entity.enums.PsuCertificacion;
import com.juanCarlos.hardwareHub.entity.enums.PsuFactorForma;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

/**
 * Clase que representa a la tabla psu de la base de datos.
 *
 * @author Juan Carlos
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "psu")
public class PsuEntity {

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
    @Column(name = "modular")
    private Boolean modular;

    @Null
    @Column(name = "potencia")
    private Integer potencia;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "certificacion")
    private PsuCertificacion certificacion;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "factor_forma")
    private PsuFactorForma factorForma;
}
