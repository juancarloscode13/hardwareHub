package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.PublicacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicacionRepository extends JpaRepository<PublicacionEntity, Long> {
    List<PublicacionEntity> findAllByOrderByFechaDesc();

    List<PublicacionEntity> getByUsuarioId(Long usuarioId);
}
