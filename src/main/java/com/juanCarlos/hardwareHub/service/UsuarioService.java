package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.request.UsuarioRequestDto;
import com.juanCarlos.hardwareHub.dto.response.UsuarioResponseDto;
import com.juanCarlos.hardwareHub.entity.UsuarioEntity;
import com.juanCarlos.hardwareHub.security.auth.dto.RegisterRequestDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UsuarioService {

    UsuarioResponseDto register(RegisterRequestDto requestDto);

    UsuarioResponseDto create(UsuarioRequestDto requestDto);

    UsuarioResponseDto getById(Long id);

    Page<UsuarioResponseDto> searchAll(String filter, int page, int size, String sort);

    UsuarioResponseDto update(Long id, UsuarioRequestDto requestDto);

    void deleteById(Long id);

    UsuarioEntity getByEmail(String email);

    // ---- Sistema de seguidores ----

    /**
     * El usuario followerId pasa a seguir al usuario followedId.
     * Lanza IllegalArgumentException si ambos IDs son iguales.
     * Lanza NoSuchElementException si algún usuario no existe.
     * No hace nada si la relación ya existe.
     */
    UsuarioResponseDto followUser(Long followerId, Long followedId);

    /**
     * El usuario followerId deja de seguir al usuario followedId.
     * No lanza error si la relación no existía.
     */
    UsuarioResponseDto unfollowUser(Long followerId, Long followedId);

    /**
     * Devuelve los seguidores del usuario con el id dado.
     */
    List<UsuarioResponseDto> getFollowers(Long userId);

    /**
     * Devuelve los usuarios a los que sigue el usuario con el id dado.
     */
    List<UsuarioResponseDto> getFollowing(Long userId);
}
