package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.RamRequestDto;
import com.juanCarlos.hardwareHub.dto.response.RamResponseDto;
import org.springframework.data.domain.Page;
import com.juanCarlos.hardwareHub.entity.enums.RamTipo;

import java.util.List;

public interface RamService {

    RamResponseDto create(RamRequestDto requestDto);

    RamResponseDto getById(Long id);

    Page<RamResponseDto> searchAll(String filter, int page, int size, String sort);

    RamResponseDto update(Long id, RamRequestDto requestDto);

    void deleteById(Long id);

    
}
