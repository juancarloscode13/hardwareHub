package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.RamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RamRepository extends JpaRepository<RamEntity, Long>, JpaSpecificationExecutor<RamEntity> {
}
