package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.RefreshTokenEntity;
import com.juanCarlos.hardwareHub.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    Optional<RefreshTokenEntity> findByToken(String token);

    void deleteByUsuario(UsuarioEntity usuario);

    void deleteAllByUsuario(UsuarioEntity usuario);

    void deleteByExpiracionBefore(Instant now);
}

