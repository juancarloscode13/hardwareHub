package com.juanCarlos.hardwareHub.security.services;

import com.juanCarlos.hardwareHub.entity.RefreshTokenEntity;
import com.juanCarlos.hardwareHub.entity.UsuarioEntity;
import com.juanCarlos.hardwareHub.exception.InvalidRefreshTokenException;
import com.juanCarlos.hardwareHub.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

/**
 * Servicio de gestión de refresh tokens opacos (UUID).
 * Cada token se persiste en BBDD y se valida contra ella.
 *
 * @author Juan Carlos
 */
@Service
@Transactional
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${app.jwt.refresh-expiration:604800}")
    private long refreshExpiration;

    /** Crea y persiste un nuevo refresh token para el usuario dado. */
    public RefreshTokenEntity createRefreshToken(UsuarioEntity usuario) {
        RefreshTokenEntity refreshToken = RefreshTokenEntity.builder()
                .token(UUID.randomUUID().toString())
                .usuario(usuario)
                .expiracion(Instant.now().plusSeconds(refreshExpiration))
                .revocado(false)
                .creadoEn(Instant.now())
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    /**
     * Valida un refresh token: comprueba existencia, revocación y expiración.
     *
     * @throws InvalidRefreshTokenException si el token no existe, está revocado o ha expirado.
     */
    public RefreshTokenEntity validateRefreshToken(String token) {
        RefreshTokenEntity refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidRefreshTokenException("Refresh token no encontrado"));

        if (refreshToken.isRevocado()) {
            throw new InvalidRefreshTokenException("Refresh token revocado");
        }

        if (refreshToken.getExpiracion().isBefore(Instant.now())) {
            throw new InvalidRefreshTokenException("Refresh token expirado");
        }

        return refreshToken;
    }

    /** Marca un refresh token como revocado en BBDD. */
    public void revokeToken(String token) {
        refreshTokenRepository.findByToken(token).ifPresent(rt -> {
            rt.setRevocado(true);
            refreshTokenRepository.save(rt);
        });
    }

    /** Revoca (elimina) todos los refresh tokens del usuario. */
    public void revokeAllUserTokens(UsuarioEntity usuario) {
        refreshTokenRepository.deleteAllByUsuario(usuario);
    }

    /** Tarea programada: limpia tokens expirados cada 24 horas. */
    @Scheduled(fixedRate = 86400000)
    public void cleanExpiredTokens() {
        refreshTokenRepository.deleteByExpiracionBefore(Instant.now());
    }
}

