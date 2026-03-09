package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.ComentarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<ComentarioEntity, Long> {
    List<ComentarioEntity> findAllByOrderByFechaDesc();
}
