package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.AlmacenamientoEntity;
import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoConectividad;
import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoFormato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlmacenamientoRepository extends JpaRepository<AlmacenamientoEntity, Long> {
    List<AlmacenamientoEntity> getByConectividad(AlmacenamientoConectividad conectividad);

    List<AlmacenamientoEntity> getByFormato(AlmacenamientoFormato formato);
}
