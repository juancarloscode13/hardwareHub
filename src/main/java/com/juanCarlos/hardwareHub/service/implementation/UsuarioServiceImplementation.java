package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dsl.filters.UsuarioFilterFields;
import com.juanCarlos.hardwareHub.dsl.search.GenericSearchService;
import com.juanCarlos.hardwareHub.dto.mappers.UsuarioMapper;
import com.juanCarlos.hardwareHub.dto.request.UsuarioRequestDto;
import com.juanCarlos.hardwareHub.dto.response.UsuarioResponseDto;
import com.juanCarlos.hardwareHub.entity.UsuarioEntity;
import com.juanCarlos.hardwareHub.repository.UsuarioRepository;
import com.juanCarlos.hardwareHub.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class UsuarioServiceImplementation implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final GenericSearchService searchService;

    @Override
    public UsuarioResponseDto create(UsuarioRequestDto requestDto) {
        UsuarioEntity entity = usuarioMapper.toEntity(requestDto);
        UsuarioEntity savedEntity = usuarioRepository.save(entity);
        return usuarioMapper.toResponseDto(savedEntity);
    }

    @Override
    public UsuarioResponseDto getById(Long id) {
        UsuarioEntity entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ningun usuario con ese id"));
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
            throw new EntityNotFoundException("No se pudo encontrar ningun usuario con ese id");
        }

        UsuarioEntity entity = usuarioMapper.toEntity(requestDto);
        entity.setId(id);
        UsuarioEntity savedEntity = usuarioRepository.save(entity);
        return usuarioMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ningun usuario con ese id");
        }
        usuarioRepository.deleteById(id);
    }
}
