package com.juanCarlos.hardwareHub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Entidad que representa un refresh token opaco (UUID) vinculado a un usuario.
 *
 * @author Juan Carlos
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "refresh_token")
public class RefreshTokenEntity {

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
    private boolean revocado;

    @Column(name = "creado_en", nullable = false, updatable = false)
    private Instant creadoEn;
}

