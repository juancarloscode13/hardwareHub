package com.juanCarlos.hardwareHub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "nombre")
    private String nombre;

    @NonNull
    @Column(name = "email")
    private String email;

    @Null
    @Column(name = "contraseña")
    private String contrasena;
}
