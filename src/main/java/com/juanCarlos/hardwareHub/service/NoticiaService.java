package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.response.NoticiaResponseDto;

import java.util.List;

public interface NoticiaService {

    List<NoticiaResponseDto> getNoticias();
}

