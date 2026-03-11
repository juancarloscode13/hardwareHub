package com.juanCarlos.hardwareHub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Clase que representa a la tabla fabricante de la base de datos.
 *
 * @author Juan Carlos
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fabricante")
public class FabricanteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "nombre")
    private String nombre;
}
