package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.PublicacionMontajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicacionMontajeRepository extends JpaRepository<PublicacionMontajeEntity, Long> {
    List<PublicacionMontajeEntity> findAllByOrderByFechaDesc();
}
