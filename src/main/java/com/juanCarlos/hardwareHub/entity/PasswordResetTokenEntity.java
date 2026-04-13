package com.juanCarlos.hardwareHub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Entidad que representa un token opaco (UUID) para la recuperación de contraseña.
 * Es de un solo uso y tiene una expiración corta (30 minutos por defecto).
 *
 * @author Juan Carlos
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "password_reset_token")
public class PasswordResetTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;

    @Column(nullable = false)
    private Instant expiracion;

    @Column(nullable = false)
    private boolean usado;

    @Column(name = "creado_en", nullable = false, updatable = false)
    private Instant creadoEn;
}

