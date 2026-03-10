package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.FabricanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FabricanteRepository extends JpaRepository<FabricanteEntity, Long>, JpaSpecificationExecutor<FabricanteEntity> {
}
