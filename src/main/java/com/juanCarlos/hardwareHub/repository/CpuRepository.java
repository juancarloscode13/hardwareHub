package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.CpuEntity;
import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CpuRepository extends JpaRepository<CpuEntity, Long> {
    List<CpuEntity> getByCpuSocket(CpuSocket cpuSocket);

    List<CpuEntity> getByConectividadPcie(Integer conectividadPcie);
}
