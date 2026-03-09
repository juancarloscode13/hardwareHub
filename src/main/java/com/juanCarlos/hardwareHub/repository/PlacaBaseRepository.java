package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.PlacaBaseEntity;
import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
import com.juanCarlos.hardwareHub.entity.enums.PlacaBaseFormato;
import com.juanCarlos.hardwareHub.entity.enums.RamTipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlacaBaseRepository extends JpaRepository<PlacaBaseEntity, Long> {
    List<PlacaBaseEntity> getBySocketCompatible(CpuSocket socketCompatible);

    List<PlacaBaseEntity> getByTipoRamSoportada(RamTipo tipoRamSoportada);

    List<PlacaBaseEntity> getByFormato(PlacaBaseFormato formato);
}
