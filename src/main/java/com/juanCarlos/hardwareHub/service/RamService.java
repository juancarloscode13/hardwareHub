package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.RamRequestDto;
import com.juanCarlos.hardwareHub.dto.response.RamResponseDto;
import com.juanCarlos.hardwareHub.entity.RamEntity;
import org.springframework.data.domain.Page;

/**
 * Servicio correspondiente a la entidad RamEntity
 *
 * @see RamEntity
 * @author Juan Carlos
 */
public interface RamService {

    RamResponseDto create(RamRequestDto requestDto);

    RamResponseDto getById(Long id);

    Page<RamResponseDto> searchAll(String filter, int page, int size, String sort);

    RamResponseDto update(Long id, RamRequestDto requestDto);

    void deleteById(Long id);

    
}
