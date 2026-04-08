package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.MontajeRequestDto;
import com.juanCarlos.hardwareHub.dto.response.MontajeResponseDto;
import com.juanCarlos.hardwareHub.entity.MontajeEntity;
import org.springframework.data.domain.Page;

/**
 * Servicio correspondiente a la entidad MontajeEntity
 *
 * @see MontajeEntity
 * @author Juan Carlos
 */
public interface MontajeService {

    MontajeResponseDto create(MontajeRequestDto requestDto);

    MontajeResponseDto getById(Long id);

    Page<MontajeResponseDto> searchAll(String filter, int page, int size, String sort);

    MontajeResponseDto update(Long id, MontajeRequestDto requestDto);

    void deleteById(Long id);

    
}
