package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.PlacaBaseRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PlacaBaseResponseDto;
import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
import com.juanCarlos.hardwareHub.entity.enums.PlacaBaseFormato;
import com.juanCarlos.hardwareHub.entity.enums.RamTipo;

import java.util.List;

public interface PlacaBaseService {

    PlacaBaseResponseDto create(PlacaBaseRequestDto requestDto);

    PlacaBaseResponseDto getById(Long id);

    List<PlacaBaseResponseDto> getAll();

    PlacaBaseResponseDto update(Long id, PlacaBaseRequestDto requestDto);

    void deleteById(Long id);

    List<PlacaBaseResponseDto> getBySocketCompatible(CpuSocket socketCompatible);

    List<PlacaBaseResponseDto> getByTipoRamSoportada(RamTipo tipoRamSoportada);

    List<PlacaBaseResponseDto> getByFormato(PlacaBaseFormato formato);
}
