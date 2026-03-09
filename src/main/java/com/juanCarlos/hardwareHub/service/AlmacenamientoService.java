package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.AlmacenamientoRequestDto;
import com.juanCarlos.hardwareHub.dto.response.AlmacenamientoResponseDto;
import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoConectividad;
import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoFormato;

import java.util.List;

public interface AlmacenamientoService {

    AlmacenamientoResponseDto create(AlmacenamientoRequestDto requestDto);

    AlmacenamientoResponseDto getById(Long id);

    List<AlmacenamientoResponseDto> getAll();

    AlmacenamientoResponseDto update(Long id, AlmacenamientoRequestDto requestDto);

    void deleteById(Long id);

    List<AlmacenamientoResponseDto> getByConectividad(AlmacenamientoConectividad conectividad);

    List<AlmacenamientoResponseDto> getByFormato(AlmacenamientoFormato formato);
}
