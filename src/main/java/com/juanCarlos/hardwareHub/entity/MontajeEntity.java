package com.juanCarlos.hardwareHub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Clase que representa a la tabla montaje de la base de datos.
 *
 * @author Juan Carlos
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "montaje")
public class MontajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_ram")
    private RamEntity ram;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cpu")
    private CpuEntity cpu;

    @Null
    @ManyToOne
    @JoinColumn(name = "id_gpu")
    private GpuEntity gpu;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_refrigeracion")
    private RefrigeracionEntity refrigeracion;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_caja")
    private CajaEntity caja;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_placa_base")
    private PlacaBaseEntity placaBase;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_psu")
    private PsuEntity psu;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_almacenamiento")
    private AlmacenamientoEntity almacenamiento;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;
}
