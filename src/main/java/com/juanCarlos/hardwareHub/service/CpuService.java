package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.CpuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.CpuResponseDto;
import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;

import java.util.List;

public interface CpuService {

    CpuResponseDto create(CpuRequestDto requestDto);

    CpuResponseDto getById(Long id);

    List<CpuResponseDto> getAll();

    CpuResponseDto update(Long id, CpuRequestDto requestDto);

    void deleteById(Long id);

    List<CpuResponseDto> getByCpuSocket(CpuSocket cpuSocket);

    List<CpuResponseDto> getByConectividadPcie(Integer conectividadPcie);
}
