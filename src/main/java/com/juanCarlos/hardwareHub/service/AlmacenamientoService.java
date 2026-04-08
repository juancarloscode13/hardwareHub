package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.AlmacenamientoRequestDto;
import com.juanCarlos.hardwareHub.dto.response.AlmacenamientoResponseDto;
import com.juanCarlos.hardwareHub.entity.AlmacenamientoEntity;
import org.springframework.data.domain.Page;

/**
 * Servicio correspondiente a la entidad AlmacenamientoEntity
 *
 * @see AlmacenamientoEntity
 * @author Juan Carlos
 */

public interface AlmacenamientoService {

    AlmacenamientoResponseDto create(AlmacenamientoRequestDto requestDto);

    AlmacenamientoResponseDto getById(Long id);

    Page<AlmacenamientoResponseDto> searchAll(String filter, int page, int size, String sort);

    AlmacenamientoResponseDto update(Long id, AlmacenamientoRequestDto requestDto);

    void deleteById(Long id);

    
}
