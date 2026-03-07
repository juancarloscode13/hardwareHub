package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.PsuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsuRepository extends JpaRepository<PsuEntity, Long> {
}
