package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dsl.filters.UsuarioFilterFields;
import com.juanCarlos.hardwareHub.dsl.search.GenericSearchService;
import com.juanCarlos.hardwareHub.dto.mappers.UsuarioMapper;
import com.juanCarlos.hardwareHub.dto.request.UsuarioRequestDto;
import com.juanCarlos.hardwareHub.dto.response.UsuarioResponseDto;
import com.juanCarlos.hardwareHub.entity.UsuarioEntity;
import com.juanCarlos.hardwareHub.entity.enums.UsuarioRol;
import com.juanCarlos.hardwareHub.exception.EmailAlreadyExistsException;
import com.juanCarlos.hardwareHub.repository.UsuarioRepository;
import com.juanCarlos.hardwareHub.security.auth.dto.RegisterRequestDto;
import com.juanCarlos.hardwareHub.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class UsuarioServiceImplementation implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final GenericSearchService searchService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioResponseDto register(RegisterRequestDto requestDto) {
        if (usuarioRepository.existsByEmail(requestDto.getEmail())) {
            throw new EmailAlreadyExistsException("Ya existe un usuario registrado con el email: " + requestDto.getEmail());
        }

        UsuarioEntity entity = new UsuarioEntity();
        entity.setNombre(requestDto.getNombre());
        entity.setEmail(requestDto.getEmail());
        entity.setContrasena(passwordEncoder.encode(requestDto.getContrasena()));
        entity.setIconoPerfil(requestDto.getIconoPerfil());
        entity.setRol(UsuarioRol.ROL_USUARIO);

        UsuarioEntity savedEntity = usuarioRepository.save(entity);
        return usuarioMapper.toResponseDto(savedEntity);
    }

    @Override
    public UsuarioResponseDto create(UsuarioRequestDto requestDto) {
        UsuarioEntity entity = usuarioMapper.toEntity(requestDto);
        UsuarioEntity savedEntity = usuarioRepository.save(entity);
        return usuarioMapper.toResponseDto(savedEntity);
    }

    @Override
    public UsuarioResponseDto getById(Long id) {
        UsuarioEntity entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ningún usuario con id: " + id));
        return usuarioMapper.toResponseDto(entity);
    }

    @Override
    public Page<UsuarioResponseDto> searchAll(String filter, int page, int size, String sort) {
        Page<UsuarioEntity> result = searchService.search(
                usuarioRepository, filter, page, size, sort,
                UsuarioFilterFields.ALLOWED_FIELDS);
        return result.map(usuarioMapper::toResponseDto);
    }

    @Override
    public UsuarioResponseDto update(Long id, UsuarioRequestDto requestDto) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ningún usuario con id: " + id);
        }
        UsuarioEntity entity = usuarioMapper.toEntity(requestDto);
        entity.setId(id);
        UsuarioEntity savedEntity = usuarioRepository.save(entity);
        return usuarioMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ningún usuario con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioEntity getByEmail(String email) {
        return usuarioRepository.getByEmail(email);
    }

    // ---- Sistema de seguidores ----

    @Override
    public UsuarioResponseDto followUser(Long followerId, Long followedId) {
        if (followerId.equals(followedId)) {
            throw new IllegalArgumentException("Un usuario no puede seguirse a sí mismo");
        }
        UsuarioEntity follower = usuarioRepository.findById(followerId)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ningún usuario con id: " + followerId));
        UsuarioEntity followed = usuarioRepository.findById(followedId)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ningún usuario con id: " + followedId));

        // Evitar duplicados silenciosamente (la PK de la tabla ya lo previene en BD)
        if (!usuarioRepository.isFollowing(followerId, followedId)) {
            follower.getUsuariosSeguidos().add(followed);
            usuarioRepository.save(follower);
        }
        return usuarioMapper.toResponseDto(follower);
    }

    @Override
    public UsuarioResponseDto unfollowUser(Long followerId, Long followedId) {
        UsuarioEntity follower = usuarioRepository.findById(followerId)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ningún usuario con id: " + followerId));
        UsuarioEntity followed = usuarioRepository.findById(followedId)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ningún usuario con id: " + followedId));

        follower.getUsuariosSeguidos().remove(followed);
        usuarioRepository.save(follower);
        return usuarioMapper.toResponseDto(follower);
    }

    @Override
    public List<UsuarioResponseDto> getFollowers(Long userId) {
        if (!usuarioRepository.existsById(userId)) {
            throw new NoSuchElementException("No se pudo encontrar ningún usuario con id: " + userId);
        }
        return usuarioRepository.findSeguidoresByUserId(userId)
                .stream()
                .map(usuarioMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UsuarioResponseDto> getFollowing(Long userId) {
        if (!usuarioRepository.existsById(userId)) {
            throw new NoSuchElementException("No se pudo encontrar ningún usuario con id: " + userId);
        }
        return usuarioRepository.findSeguidosByUserId(userId)
                .stream()
                .map(usuarioMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
