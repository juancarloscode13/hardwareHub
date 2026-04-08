package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.FabricanteRequestDto;
import com.juanCarlos.hardwareHub.dto.response.FabricanteResponseDto;
import com.juanCarlos.hardwareHub.entity.FabricanteEntity;
import org.springframework.data.domain.Page;

/**
 * Servicio correspondiente a la entidad FabricanteEntity
 *
 * @see FabricanteEntity
 * @author Juan Carlos
 */
public interface FabricanteService {

    FabricanteResponseDto create(FabricanteRequestDto requestDto);

    FabricanteResponseDto getById(Long id);

    Page<FabricanteResponseDto> searchAll(String filter, int page, int size, String sort);

    FabricanteResponseDto update(Long id, FabricanteRequestDto requestDto);

    void deleteById(Long id);
}
