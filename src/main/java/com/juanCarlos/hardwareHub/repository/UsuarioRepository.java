package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>, JpaSpecificationExecutor<UsuarioEntity> {
}
