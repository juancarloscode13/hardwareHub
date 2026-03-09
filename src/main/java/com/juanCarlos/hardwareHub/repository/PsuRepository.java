package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.PsuEntity;
import com.juanCarlos.hardwareHub.entity.enums.PsuFactorForma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PsuRepository extends JpaRepository<PsuEntity, Long> {
    List<PsuEntity> getByFactorForma(PsuFactorForma factorForma);

    List<PsuEntity> getByPotenciaGreaterThanEqual(Integer potencia);
}
