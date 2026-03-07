package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
