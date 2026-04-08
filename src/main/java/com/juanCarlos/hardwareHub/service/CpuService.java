package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.CpuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.CpuResponseDto;
import com.juanCarlos.hardwareHub.entity.CpuEntity;
import org.springframework.data.domain.Page;

/**
 * Servicio correspondiente a la entidad CpuEntity
 *
 * @see CpuEntity
 * @author Juan Carlos
 */
public interface CpuService {

    CpuResponseDto create(CpuRequestDto requestDto);

    CpuResponseDto getById(Long id);

    Page<CpuResponseDto> searchAll(String filter, int page, int size, String sort);

    CpuResponseDto update(Long id, CpuRequestDto requestDto);

    void deleteById(Long id);
}
