package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.RamRequestDto;
import com.juanCarlos.hardwareHub.dto.response.RamResponseDto;
import com.juanCarlos.hardwareHub.entity.enums.RamTipo;

import java.util.List;

public interface RamService {

    RamResponseDto create(RamRequestDto requestDto);

    RamResponseDto getById(Long id);

    List<RamResponseDto> getAll();

    RamResponseDto update(Long id, RamRequestDto requestDto);

    void deleteById(Long id);

    List<RamResponseDto> getByTipo(RamTipo tipo);
}
