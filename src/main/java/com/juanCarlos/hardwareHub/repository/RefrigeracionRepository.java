package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.RefrigeracionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RefrigeracionRepository extends JpaRepository<RefrigeracionEntity, Long>, JpaSpecificationExecutor<RefrigeracionEntity> {
}
