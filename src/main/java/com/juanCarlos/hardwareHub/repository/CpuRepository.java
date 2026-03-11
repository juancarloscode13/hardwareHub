package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.CpuEntity;
import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CpuRepository extends JpaRepository<CpuEntity, Long>, JpaSpecificationExecutor<CpuEntity> {}
