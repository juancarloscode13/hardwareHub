package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.AlmacenamientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repositorio correspondiente a la entidad AlmacenamientoEntity
 *
 * @see AlmacenamientoEntity
 * @author Juan Carlos
 */
public interface AlmacenamientoRepository extends JpaRepository<AlmacenamientoEntity, Long>, JpaSpecificationExecutor<AlmacenamientoEntity> {
}
