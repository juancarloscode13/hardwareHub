package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.PlacaBaseRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PlacaBaseResponseDto;
import com.juanCarlos.hardwareHub.entity.PlacaBaseEntity;
import org.springframework.data.domain.Page;

/**
 * Servicio correspondiente a la entidad PlacaBaseEntity
 *
 * @see PlacaBaseEntity
 * @author Juan Carlos
 */
public interface PlacaBaseService {

    PlacaBaseResponseDto create(PlacaBaseRequestDto requestDto);

    PlacaBaseResponseDto getById(Long id);

    Page<PlacaBaseResponseDto> searchAll(String filter, int page, int size, String sort);

    PlacaBaseResponseDto update(Long id, PlacaBaseRequestDto requestDto);

    void deleteById(Long id);

    
}
