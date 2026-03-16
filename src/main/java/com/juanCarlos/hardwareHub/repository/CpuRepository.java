package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.CpuEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CpuRepository extends JpaRepository<CpuEntity, Long>, JpaSpecificationExecutor<CpuEntity> {}
