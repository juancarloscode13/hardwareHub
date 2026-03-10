package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.CajaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CajaRepository extends JpaRepository<CajaEntity, Long>, JpaSpecificationExecutor<CajaEntity> {
}
