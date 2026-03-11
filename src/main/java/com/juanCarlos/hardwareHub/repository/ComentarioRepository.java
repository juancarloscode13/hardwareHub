package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.ComentarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ComentarioRepository extends JpaRepository<ComentarioEntity, Long>, JpaSpecificationExecutor<ComentarioEntity> {
}
