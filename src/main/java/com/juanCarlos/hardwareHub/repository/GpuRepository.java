package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.GpuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repositorio correspondiente a la entidad GpuEntity
 *
 * @see GpuEntity
 * @author Juan Carlos
 */
public interface GpuRepository extends JpaRepository<GpuEntity, Long>, JpaSpecificationExecutor<GpuEntity> {
}
