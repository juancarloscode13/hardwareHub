package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.RamEntity;
import com.juanCarlos.hardwareHub.entity.enums.RamTipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RamRepository extends JpaRepository<RamEntity, Long> {
    List<RamEntity> getByTipo(RamTipo tipo);
}
