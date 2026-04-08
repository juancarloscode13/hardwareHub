package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.PublicacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repositorio correspondiente a la entidad PublicacionEntity
 *
 * @see PublicacionEntity
 * @author Juan Carlos
 */
public interface PublicacionRepository extends JpaRepository<PublicacionEntity, Long>, JpaSpecificationExecutor<PublicacionEntity> {
}
