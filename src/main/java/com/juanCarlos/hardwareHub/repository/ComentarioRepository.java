package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.ComentarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repositorio correspondiente a la entidad ComentarioEntity
 *
 * @see ComentarioEntity
 * @author Juan Carlos
 */
public interface ComentarioRepository extends JpaRepository<ComentarioEntity, Long>, JpaSpecificationExecutor<ComentarioEntity> {
}
