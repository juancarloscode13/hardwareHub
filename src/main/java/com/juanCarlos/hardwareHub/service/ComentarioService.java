package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.ComentarioRequestDto;
import com.juanCarlos.hardwareHub.dto.response.ComentarioResponseDto;

import java.util.List;

public interface ComentarioService {

    ComentarioResponseDto create(ComentarioRequestDto requestDto);

    ComentarioResponseDto getById(Long id);

    List<ComentarioResponseDto> getAll();

    ComentarioResponseDto update(Long id, ComentarioRequestDto requestDto);

    void deleteById(Long id);

    List<ComentarioResponseDto> getAllOrderByFechaDesc();

    List<ComentarioResponseDto> getByUsuarioId(Long usuarioId);

    List<ComentarioResponseDto> getByPublicacionId(Long publicacionId);

    List<ComentarioResponseDto> getByComentarioId(Long comentarioId);
}
