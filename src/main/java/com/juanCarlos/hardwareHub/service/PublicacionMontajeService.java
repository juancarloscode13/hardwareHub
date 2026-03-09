package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.PublicacionMontajeRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PublicacionMontajeResponseDto;

import java.util.List;

public interface PublicacionMontajeService {

    PublicacionMontajeResponseDto create(PublicacionMontajeRequestDto requestDto);

    PublicacionMontajeResponseDto getById(Long id);

    List<PublicacionMontajeResponseDto> getAll();

    PublicacionMontajeResponseDto update(Long id, PublicacionMontajeRequestDto requestDto);

    void deleteById(Long id);
}
