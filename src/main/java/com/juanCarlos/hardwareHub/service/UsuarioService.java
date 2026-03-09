package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.UsuarioRequestDto;
import com.juanCarlos.hardwareHub.dto.response.UsuarioResponseDto;

import java.util.List;

public interface UsuarioService {

    UsuarioResponseDto create(UsuarioRequestDto requestDto);

    UsuarioResponseDto getById(Long id);

    List<UsuarioResponseDto> getAll();

    UsuarioResponseDto update(Long id, UsuarioRequestDto requestDto);

    void deleteById(Long id);
}
