package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.FabricanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repositorio correspondiente a la entidad FabricanteEntity
 *
 * @see FabricanteEntity
 * @author Juan Carlos
 */
public interface FabricanteRepository extends JpaRepository<FabricanteEntity, Long>, JpaSpecificationExecutor<FabricanteEntity> {
}
