package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.PlacaBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repositorio correspondiente a la entidad PlacaBaseEntity
 *
 * @see PlacaBaseEntity
 * @author Juan Carlos
 */
public interface PlacaBaseRepository extends JpaRepository<PlacaBaseEntity, Long>, JpaSpecificationExecutor<PlacaBaseEntity> {
}
