package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.GpuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GpuRepository extends JpaRepository<GpuEntity, Long>, JpaSpecificationExecutor<GpuEntity> {
}
