package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.AlmacenamientoRequestDto;
import com.juanCarlos.hardwareHub.dto.response.AlmacenamientoResponseDto;
import org.springframework.data.domain.Page;
import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoConectividad;
import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoFormato;

import java.util.List;

public interface AlmacenamientoService {

    AlmacenamientoResponseDto create(AlmacenamientoRequestDto requestDto);

    AlmacenamientoResponseDto getById(Long id);

    Page<AlmacenamientoResponseDto> searchAll(String filter, int page, int size, String sort);

    AlmacenamientoResponseDto update(Long id, AlmacenamientoRequestDto requestDto);

    void deleteById(Long id);

    
}
