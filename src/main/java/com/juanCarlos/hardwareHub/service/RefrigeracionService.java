package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.RefrigeracionRequestDto;
import com.juanCarlos.hardwareHub.dto.response.RefrigeracionResponseDto;
import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;

import java.util.List;

public interface RefrigeracionService {

    RefrigeracionResponseDto create(RefrigeracionRequestDto requestDto);

    RefrigeracionResponseDto getById(Long id);

    List<RefrigeracionResponseDto> getAll();

    RefrigeracionResponseDto update(Long id, RefrigeracionRequestDto requestDto);

    void deleteById(Long id);

    List<RefrigeracionResponseDto> getBySocketCompatible(CpuSocket socketCompatible);
}
