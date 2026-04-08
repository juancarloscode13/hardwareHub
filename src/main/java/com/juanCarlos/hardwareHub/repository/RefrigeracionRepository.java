package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.RefrigeracionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repositorio correspondiente a la entidad RefrigeracionEntity
 *
 * @see RefrigeracionEntity
 * @author Juan Carlos
 */
public interface RefrigeracionRepository extends JpaRepository<RefrigeracionEntity, Long>, JpaSpecificationExecutor<RefrigeracionEntity> {
}
