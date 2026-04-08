package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.CajaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repositorio correspondiente a la entidad CajaEntity
 *
 * @see CajaEntity
 * @author Juan Carlos
 */
public interface CajaRepository extends JpaRepository<CajaEntity, Long>, JpaSpecificationExecutor<CajaEntity> {
}
