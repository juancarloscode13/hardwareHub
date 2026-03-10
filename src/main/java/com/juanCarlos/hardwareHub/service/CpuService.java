package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.CpuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.CpuResponseDto;
import org.springframework.data.domain.Page;

public interface CpuService {

    CpuResponseDto create(CpuRequestDto requestDto);

    CpuResponseDto getById(Long id);

    Page<CpuResponseDto> searchAll(String filter, int page, int size, String sort);

    CpuResponseDto update(Long id, CpuRequestDto requestDto);

    void deleteById(Long id);
}
