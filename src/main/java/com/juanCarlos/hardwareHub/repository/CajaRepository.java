package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.CajaEntity;
import com.juanCarlos.hardwareHub.entity.enums.CajaFormato;
import com.juanCarlos.hardwareHub.entity.enums.PsuFactorForma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CajaRepository extends JpaRepository<CajaEntity, Long> {
    List<CajaEntity> getByPlacasBaseCompatibles(CajaFormato placasBaseCompatibles);

    List<CajaEntity> getByPsuCompatible(PsuFactorForma psuCompatible);

    List<CajaEntity> getByAlturaMaxEnfriadorCpuGreaterThanEqual(Integer alturaMaxEnfriadorCpu);
}
