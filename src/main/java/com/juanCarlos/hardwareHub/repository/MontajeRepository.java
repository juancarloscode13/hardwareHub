package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.MontajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repositorio correspondiente a la entidad MontajeEntity
 *
 * @see MontajeEntity
 * @author Juan Carlos
 */
public interface MontajeRepository extends JpaRepository<MontajeEntity, Long>, JpaSpecificationExecutor<MontajeEntity> {
}
