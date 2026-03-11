package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.RefrigeracionRequestDto;
import com.juanCarlos.hardwareHub.dto.response.RefrigeracionResponseDto;
import org.springframework.data.domain.Page;
import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;

import java.util.List;

public interface RefrigeracionService {

    RefrigeracionResponseDto create(RefrigeracionRequestDto requestDto);

    RefrigeracionResponseDto getById(Long id);

    Page<RefrigeracionResponseDto> searchAll(String filter, int page, int size, String sort);

    RefrigeracionResponseDto update(Long id, RefrigeracionRequestDto requestDto);

    void deleteById(Long id);

    
}
