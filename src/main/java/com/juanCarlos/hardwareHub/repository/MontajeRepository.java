package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.MontajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MontajeRepository extends JpaRepository<MontajeEntity, Long> {
    List<MontajeEntity> getByUsuarioId(Long usuarioId);
}
