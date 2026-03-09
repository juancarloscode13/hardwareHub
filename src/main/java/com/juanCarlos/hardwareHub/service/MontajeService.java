package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.MontajeRequestDto;
import com.juanCarlos.hardwareHub.dto.response.MontajeResponseDto;

import java.util.List;

public interface MontajeService {

    MontajeResponseDto create(MontajeRequestDto requestDto);

    MontajeResponseDto getById(Long id);

    List<MontajeResponseDto> getAll();

    MontajeResponseDto update(Long id, MontajeRequestDto requestDto);

    void deleteById(Long id);
}
