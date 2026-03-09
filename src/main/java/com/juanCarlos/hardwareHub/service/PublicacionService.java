package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.PublicacionRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PublicacionResponseDto;

import java.util.List;

public interface PublicacionService {

    PublicacionResponseDto create(PublicacionRequestDto requestDto);

    PublicacionResponseDto getById(Long id);

    List<PublicacionResponseDto> getAll();

    PublicacionResponseDto update(Long id, PublicacionRequestDto requestDto);

    void deleteById(Long id);

    List<PublicacionResponseDto> getAllOrderByFechaDesc();

    List<PublicacionResponseDto> getByUsuarioId(Long usuarioId);
}
