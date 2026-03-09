package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.GpuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.GpuResponseDto;

import java.util.List;

public interface GpuService {

    GpuResponseDto create(GpuRequestDto requestDto);

    GpuResponseDto getById(Long id);

    List<GpuResponseDto> getAll();

    GpuResponseDto update(Long id, GpuRequestDto requestDto);

    void deleteById(Long id);

    List<GpuResponseDto> getByConectividadPcie(Integer conectividadPcie);

    List<GpuResponseDto> getByAltoGpuLessThanEqual(Integer altoGpu);
}
