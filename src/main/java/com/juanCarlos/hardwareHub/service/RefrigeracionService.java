package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.RefrigeracionRequestDto;
import com.juanCarlos.hardwareHub.dto.response.RefrigeracionResponseDto;
import com.juanCarlos.hardwareHub.entity.RefrigeracionEntity;
import org.springframework.data.domain.Page;

/**
 * Servicio correspondiente a la entidad RefrigeracionEntity
 *
 * @see RefrigeracionEntity
 * @author Juan Carlos
 */
public interface RefrigeracionService {

    RefrigeracionResponseDto create(RefrigeracionRequestDto requestDto);

    RefrigeracionResponseDto getById(Long id);

    Page<RefrigeracionResponseDto> searchAll(String filter, int page, int size, String sort);

    RefrigeracionResponseDto update(Long id, RefrigeracionRequestDto requestDto);

    void deleteById(Long id);

    
}
