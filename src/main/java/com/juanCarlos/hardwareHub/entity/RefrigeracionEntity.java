package com.juanCarlos.hardwareHub.entity;

import com.juanCarlos.hardwareHub.entity.enums.RefrigeracionTipo;
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

/**
 * Clase que representa a la tabla refrigeracion de la base de datos.
 *
 * @author Juan Carlos
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "refrigeracion")
public class RefrigeracionEntity {

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
    @Column(name = "socket_compatible")
    private String socketCompatible;

    @NonNull
    @Column(name = "tipo")
    private RefrigeracionTipo tipo;

    @Null
    @JdbcTypeCode(value = SqlTypes.JSON)
    @Column(name = "atributos")
    private Map<String, Object> atributos;
}
