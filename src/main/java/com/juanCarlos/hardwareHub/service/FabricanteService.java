package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.FabricanteRequestDto;
import com.juanCarlos.hardwareHub.dto.response.FabricanteResponseDto;

import java.util.List;

public interface FabricanteService {

    FabricanteResponseDto create(FabricanteRequestDto requestDto);

    FabricanteResponseDto getById(Long id);

    List<FabricanteResponseDto> getAll();

    FabricanteResponseDto update(Long id, FabricanteRequestDto requestDto);

    void deleteById(Long id);
}
