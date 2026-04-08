package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.ComentarioRequestDto;
import com.juanCarlos.hardwareHub.dto.response.ComentarioResponseDto;
import com.juanCarlos.hardwareHub.entity.ComentarioEntity;
import org.springframework.data.domain.Page;

/**
 * Servicio correspondiente a la entidad ComentarioEntity
 *
 * @see ComentarioEntity
 * @author Juan Carlos
 */
public interface ComentarioService {

    ComentarioResponseDto create(ComentarioRequestDto requestDto);

    ComentarioResponseDto getById(Long id);

    Page<ComentarioResponseDto> searchAll(String filter, int page, int size, String sort);

    ComentarioResponseDto update(Long id, ComentarioRequestDto requestDto);

    void deleteById(Long id);

    
}
