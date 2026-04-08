package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.PsuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repositorio correspondiente a la entidad PsuEntity
 *
 * @see PsuEntity
 * @author Juan Carlos
 */
public interface PsuRepository extends JpaRepository<PsuEntity, Long>, JpaSpecificationExecutor<PsuEntity> {
}
