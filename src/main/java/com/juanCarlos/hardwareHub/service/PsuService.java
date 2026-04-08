package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.PsuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PsuResponseDto;
import com.juanCarlos.hardwareHub.entity.PsuEntity;
import org.springframework.data.domain.Page;

/**
 * Servicio correspondiente a la entidad PsuEntity
 *
 * @see PsuEntity
 * @author Juan Carlos
 */
public interface PsuService {

    PsuResponseDto create(PsuRequestDto requestDto);

    PsuResponseDto getById(Long id);

    Page<PsuResponseDto> searchAll(String filter, int page, int size, String sort);

    PsuResponseDto update(Long id, PsuRequestDto requestDto);

    void deleteById(Long id);

    
}
