package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.RamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repositorio correspondiente a la entidad RamEntity
 *
 * @see RamEntity
 * @author Juan Carlos
 */
public interface RamRepository extends JpaRepository<RamEntity, Long>, JpaSpecificationExecutor<RamEntity> {
}
