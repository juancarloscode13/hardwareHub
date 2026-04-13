package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.PasswordResetTokenEntity;
import com.juanCarlos.hardwareHub.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;

/**
 * Repositorio correspondiente a la entidad PasswordResetTokenEntity
 *
 * @see PasswordResetTokenEntity
 * @author Juan Carlos
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity, Long> {

    Optional<PasswordResetTokenEntity> findByToken(String token);

    void deleteAllByUsuario(UsuarioEntity usuario);

    void deleteByExpiracionBefore(Instant now);
}

