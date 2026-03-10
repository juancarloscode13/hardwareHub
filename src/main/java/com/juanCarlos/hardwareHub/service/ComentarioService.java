package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.ComentarioRequestDto;
import com.juanCarlos.hardwareHub.dto.response.ComentarioResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ComentarioService {

    ComentarioResponseDto create(ComentarioRequestDto requestDto);

    ComentarioResponseDto getById(Long id);

    Page<ComentarioResponseDto> searchAll(String filter, int page, int size, String sort);

    ComentarioResponseDto update(Long id, ComentarioRequestDto requestDto);

    void deleteById(Long id);

    
}
