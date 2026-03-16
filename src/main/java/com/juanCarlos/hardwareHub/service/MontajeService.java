package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.MontajeRequestDto;
import com.juanCarlos.hardwareHub.dto.response.MontajeResponseDto;
import org.springframework.data.domain.Page;

public interface MontajeService {

    MontajeResponseDto create(MontajeRequestDto requestDto);

    MontajeResponseDto getById(Long id);

    Page<MontajeResponseDto> searchAll(String filter, int page, int size, String sort);

    MontajeResponseDto update(Long id, MontajeRequestDto requestDto);

    void deleteById(Long id);

    
}
