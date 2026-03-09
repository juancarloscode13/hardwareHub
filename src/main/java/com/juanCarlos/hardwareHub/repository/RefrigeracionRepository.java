package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.RefrigeracionEntity;
import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefrigeracionRepository extends JpaRepository<RefrigeracionEntity, Long> {
    List<RefrigeracionEntity> getBySocketCompatible(CpuSocket socketCompatible);
}
