package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.response.NoticiaResponseDto;

import java.util.List;

/**
 * Servicio correspondiente a las noticias (no necesariamente persistidas como entidad)
 *
 * @see NoticiaResponseDto
 * @author Juan Carlos
 */
public interface NoticiaService {

    List<NoticiaResponseDto> getNoticias();
}

