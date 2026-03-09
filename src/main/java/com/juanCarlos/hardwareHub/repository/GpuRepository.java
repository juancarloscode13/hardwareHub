package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.GpuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GpuRepository extends JpaRepository<GpuEntity, Long> {
    List<GpuEntity> getByConectividadPcie(Integer conectividadPcie);

    List<GpuEntity> getByAltoGpuLessThanEqual(Integer altoGpu);

    List<GpuEntity> getByPuntuacionPassmarkGreaterThanEqual(Integer puntuacionPassmark);

    List<GpuEntity> findAllByOrderByPuntuacionPassmarkDesc();
}
