package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.MontajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MontajeRepository extends JpaRepository<MontajeEntity, Long>, JpaSpecificationExecutor<MontajeEntity> {
}
