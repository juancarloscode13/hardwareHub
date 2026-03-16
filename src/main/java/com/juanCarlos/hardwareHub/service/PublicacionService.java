package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.PublicacionRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PublicacionResponseDto;
import org.springframework.data.domain.Page;

public interface PublicacionService {

    PublicacionResponseDto create(PublicacionRequestDto requestDto);

    PublicacionResponseDto getById(Long id);

    Page<PublicacionResponseDto> searchAll(String filter, int page, int size, String sort);

    PublicacionResponseDto update(Long id, PublicacionRequestDto requestDto);

    void deleteById(Long id);

    
}
