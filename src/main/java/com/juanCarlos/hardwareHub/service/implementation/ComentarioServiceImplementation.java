package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dto.mappers.ComentarioMapper;
import com.juanCarlos.hardwareHub.dto.request.ComentarioRequestDto;
import com.juanCarlos.hardwareHub.dto.response.ComentarioResponseDto;
import com.juanCarlos.hardwareHub.entity.ComentarioEntity;
import com.juanCarlos.hardwareHub.repository.ComentarioRepository;
import com.juanCarlos.hardwareHub.service.ComentarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class ComentarioServiceImplementation implements ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;

    @Override
    public ComentarioResponseDto create(ComentarioRequestDto requestDto) {
        ComentarioEntity entity = comentarioMapper.toEntity(requestDto);
        ComentarioEntity savedEntity = comentarioRepository.save(entity);
        return comentarioMapper.toResponseDto(savedEntity);
    }

    @Override
    public ComentarioResponseDto getById(Long id) {
        ComentarioEntity entity = comentarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ningun comentario con ese id"));
        return comentarioMapper.toResponseDto(entity);
    }

    @Override
    public List<ComentarioResponseDto> getAll() {
        List<ComentarioEntity> entities = comentarioRepository.findAll();
        return comentarioMapper.toResponseDtoList(entities);
    }

    @Override
    public ComentarioResponseDto update(Long id, ComentarioRequestDto requestDto) {
        if (!comentarioRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ningun comentario con ese id");
        }

        ComentarioEntity entity = comentarioMapper.toEntity(requestDto);
        entity.setId(id);
        ComentarioEntity savedEntity = comentarioRepository.save(entity);
        return comentarioMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ningun comentario con ese id");
        }
        comentarioRepository.deleteById(id);
    }
}
