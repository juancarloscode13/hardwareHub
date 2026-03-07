package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.GpuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GpuRepository extends JpaRepository<GpuEntity, Long> {
}
