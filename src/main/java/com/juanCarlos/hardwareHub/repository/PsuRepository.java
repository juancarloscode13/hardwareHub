package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.PsuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PsuRepository extends JpaRepository<PsuEntity, Long>, JpaSpecificationExecutor<PsuEntity> {
}
