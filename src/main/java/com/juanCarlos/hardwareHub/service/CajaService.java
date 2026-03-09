package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.CajaRequestDto;
import com.juanCarlos.hardwareHub.dto.response.CajaResponseDto;
import com.juanCarlos.hardwareHub.entity.enums.CajaFormato;
import com.juanCarlos.hardwareHub.entity.enums.PsuFactorForma;

import java.util.List;

public interface CajaService {

    CajaResponseDto create(CajaRequestDto requestDto);

    CajaResponseDto getById(Long id);

    List<CajaResponseDto> getAll();

    CajaResponseDto update(Long id, CajaRequestDto requestDto);

    void deleteById(Long id);

    List<CajaResponseDto> getByPlacasBaseCompatibles(CajaFormato placasBaseCompatibles);

    List<CajaResponseDto> getByPsuCompatible(PsuFactorForma psuCompatible);

    List<CajaResponseDto> getByAlturaMaxEnfriadorCpuGreaterThanEqual(Integer alturaMaxEnfriadorCpu);
}
