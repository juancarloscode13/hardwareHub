package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.CajaRequestDto;
import com.juanCarlos.hardwareHub.dto.response.CajaResponseDto;
import org.springframework.data.domain.Page;
import com.juanCarlos.hardwareHub.entity.enums.CajaFormato;
import com.juanCarlos.hardwareHub.entity.enums.PsuFactorForma;

import java.util.List;

public interface CajaService {

    CajaResponseDto create(CajaRequestDto requestDto);

    CajaResponseDto getById(Long id);

    Page<CajaResponseDto> searchAll(String filter, int page, int size, String sort);

    CajaResponseDto update(Long id, CajaRequestDto requestDto);

    void deleteById(Long id);

    
}
