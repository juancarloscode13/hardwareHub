package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.PsuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PsuResponseDto;
import com.juanCarlos.hardwareHub.entity.enums.PsuFactorForma;

import java.util.List;

public interface PsuService {

    PsuResponseDto create(PsuRequestDto requestDto);

    PsuResponseDto getById(Long id);

    List<PsuResponseDto> getAll();

    PsuResponseDto update(Long id, PsuRequestDto requestDto);

    void deleteById(Long id);

    List<PsuResponseDto> getByFactorForma(PsuFactorForma factorForma);

    List<PsuResponseDto> getByPotenciaGreaterThanEqual(Integer potencia);
}
