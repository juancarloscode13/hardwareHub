package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.GpuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.GpuResponseDto;
import org.springframework.data.domain.Page;

public interface GpuService {

    GpuResponseDto create(GpuRequestDto requestDto);

    GpuResponseDto getById(Long id);

    Page<GpuResponseDto> searchAll(String filter, int page, int size, String sort);

    GpuResponseDto update(Long id, GpuRequestDto requestDto);

    void deleteById(Long id);
}
