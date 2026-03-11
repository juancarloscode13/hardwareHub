package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.UsuarioRequestDto;
import com.juanCarlos.hardwareHub.dto.response.UsuarioResponseDto;
import org.springframework.data.domain.Page;

public interface UsuarioService {

    UsuarioResponseDto create(UsuarioRequestDto requestDto);

    UsuarioResponseDto getById(Long id);

    Page<UsuarioResponseDto> searchAll(String filter, int page, int size, String sort);

    UsuarioResponseDto update(Long id, UsuarioRequestDto requestDto);

    void deleteById(Long id);
}
