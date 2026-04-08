package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.CajaRequestDto;
import com.juanCarlos.hardwareHub.dto.response.CajaResponseDto;
import com.juanCarlos.hardwareHub.entity.CajaEntity;
import org.springframework.data.domain.Page;

/**
 * Servicio correspondiente a la entidad CajaEntity
 *
 * @see CajaEntity
 * @author Juan Carlos
 */
public interface CajaService {

    CajaResponseDto create(CajaRequestDto requestDto);

    CajaResponseDto getById(Long id);

    Page<CajaResponseDto> searchAll(String filter, int page, int size, String sort);

    CajaResponseDto update(Long id, CajaRequestDto requestDto);

    void deleteById(Long id);

    
}
