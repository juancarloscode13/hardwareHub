package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.PublicacionRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PublicacionResponseDto;
import com.juanCarlos.hardwareHub.entity.PublicacionEntity;
import org.springframework.data.domain.Page;

/**
 * Servicio correspondiente a la entidad PublicacionEntity
 *
 * @see PublicacionEntity
 * @author Juan Carlos
 */
public interface PublicacionService {

    PublicacionResponseDto create(PublicacionRequestDto requestDto);

    PublicacionResponseDto getById(Long id);

    Page<PublicacionResponseDto> searchAll(String filter, int page, int size, String sort);

    PublicacionResponseDto update(Long id, PublicacionRequestDto requestDto);

    void deleteById(Long id);

    
}
