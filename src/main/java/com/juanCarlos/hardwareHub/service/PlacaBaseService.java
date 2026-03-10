package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.PlacaBaseRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PlacaBaseResponseDto;
import org.springframework.data.domain.Page;
import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
import com.juanCarlos.hardwareHub.entity.enums.PlacaBaseFormato;
import com.juanCarlos.hardwareHub.entity.enums.RamTipo;

import java.util.List;

public interface PlacaBaseService {

    PlacaBaseResponseDto create(PlacaBaseRequestDto requestDto);

    PlacaBaseResponseDto getById(Long id);

    Page<PlacaBaseResponseDto> searchAll(String filter, int page, int size, String sort);

    PlacaBaseResponseDto update(Long id, PlacaBaseRequestDto requestDto);

    void deleteById(Long id);

    
}
