package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.entity.PasswordResetTokenEntity;
import com.juanCarlos.hardwareHub.entity.UsuarioEntity;
import com.juanCarlos.hardwareHub.exception.InvalidPasswordResetTokenException;
import com.juanCarlos.hardwareHub.repository.PasswordResetTokenRepository;
import com.juanCarlos.hardwareHub.repository.UsuarioRepository;
import com.juanCarlos.hardwareHub.security.services.RefreshTokenService;
import com.juanCarlos.hardwareHub.service.EmailService;
import com.juanCarlos.hardwareHub.service.PasswordResetService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

/**
 * Implementación del servicio de recuperación de contraseña.
 * Gestiona la generación de tokens de reset, el envío de emails
 * y el restablecimiento de la contraseña del usuario.
 *
 * @see PasswordResetService
 * @author Juan Carlos
 */
@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class PasswordResetServiceImplementation implements PasswordResetService {

    private final PasswordResetTokenRepository tokenRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.frontend.url:http://localhost:5173}")
    private String frontendUrl;

    @Value("${app.password-reset.expiration:1800}")
    private long tokenExpiration;

    /** {@inheritDoc} */
    @Override
    public void requestPasswordReset(String email) {
        UsuarioEntity usuario = usuarioRepository.getByEmail(email);

        // Anti-enumeración: si el usuario no existe, no hacemos nada pero respondemos 200
        if (usuario == null) {
            log.warn("Solicitud de reset para email no registrado: {}", email);
            return;
        }

        // Invalidar tokens de reset anteriores del usuario
        tokenRepository.deleteAllByUsuario(usuario);

        // Generar nuevo token
        PasswordResetTokenEntity resetToken = PasswordResetTokenEntity.builder()
                .token(UUID.randomUUID().toString())
                .usuario(usuario)
                .expiracion(Instant.now().plusSeconds(tokenExpiration))
                .usado(false)
                .creadoEn(Instant.now())
                .build();
        tokenRepository.save(resetToken);

        // Construir enlace y enviar email
        String resetLink = frontendUrl + "/reset-password?token=" + resetToken.getToken();
        try {
            emailService.sendPasswordResetEmail(usuario.getEmail(), resetLink);
        } catch (RuntimeException e) {
            // No exponemos errores de infraestructura y evitamos persistir un token no entregado.
            tokenRepository.delete(resetToken);
            log.error("No se pudo enviar el email de reset para usuario id={}", usuario.getId(), e);
            return;
        }

        log.info("Token de reset generado para usuario id={}", usuario.getId());
    }

    /** {@inheritDoc} */
    @Override
    public void resetPassword(String token, String nuevaContrasena) {
        PasswordResetTokenEntity resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidPasswordResetTokenException("El enlace de recuperación no es válido"));

        if (resetToken.isUsado()) {
            throw new InvalidPasswordResetTokenException("Este enlace de recuperación ya fue utilizado");
        }

        if (resetToken.getExpiracion().isBefore(Instant.now())) {
            throw new InvalidPasswordResetTokenException("El enlace de recuperación ha expirado");
        }

        // Actualizar contraseña
        UsuarioEntity usuario = resetToken.getUsuario();
        usuario.setContrasena(passwordEncoder.encode(nuevaContrasena));
        usuarioRepository.save(usuario);

        // Marcar token como usado
        resetToken.setUsado(true);
        tokenRepository.save(resetToken);

        // Revocar todas las sesiones activas del usuario
        refreshTokenService.revokeAllUserTokens(usuario);

        log.info("Contraseña restablecida para usuario id={}", usuario.getId());
    }
}

