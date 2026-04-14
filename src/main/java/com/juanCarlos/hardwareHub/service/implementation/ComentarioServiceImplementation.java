package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dsl.filters.ComentarioFilterFields;
import com.juanCarlos.hardwareHub.dsl.search.GenericSearchService;
import com.juanCarlos.hardwareHub.dto.mappers.ComentarioMapper;
import com.juanCarlos.hardwareHub.dto.request.ComentarioRequestDto;
import com.juanCarlos.hardwareHub.dto.response.ComentarioResponseDto;
import com.juanCarlos.hardwareHub.entity.ComentarioEntity;
import com.juanCarlos.hardwareHub.repository.ComentarioRepository;
import com.juanCarlos.hardwareHub.service.ComentarioService;
import com.juanCarlos.hardwareHub.service.ForumEventPublisher;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.time.LocalDateTime;

/**
 * Implementacion del servicio de la entidad ComentarioEntity
 *
 * @see ComentarioService
 * @author Juan Carlos
 */
@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ComentarioServiceImplementation implements ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;
    private final GenericSearchService searchService;
    private final ForumEventPublisher forumEventPublisher;

    @Override
    public ComentarioResponseDto create(ComentarioRequestDto requestDto) {
        log.info("Creando nuevo comentario para publicacionId={}", requestDto.getPublicacionId());
        ComentarioEntity entity = comentarioMapper.toEntity(requestDto);
        entity.setFecha(LocalDateTime.now());
        ComentarioEntity savedEntity = comentarioRepository.save(entity);
        ComentarioResponseDto responseDto = comentarioMapper.toResponseDto(savedEntity);
        // Broadcast a todos los usuarios que estén viendo esta publicación
        forumEventPublisher.publishNuevoComentario(responseDto);
        log.info("Comentario creado exitosamente: id={}, publicacionId={}", savedEntity.getId(), requestDto.getPublicacionId());
        return responseDto;
    }

    @Override
    public ComentarioResponseDto getById(Long id) {
        log.info("Obteniendo comentario: id={}", id);
        ComentarioEntity entity = comentarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ningun comentario con ese id"));
        log.info("Comentario obtenido exitosamente: id={}", id);
        return comentarioMapper.toResponseDto(entity);
    }

    @Override
    public Page<ComentarioResponseDto> searchAll(String filter, int page, int size, String sort) {
        Page<ComentarioEntity> result = searchService.search(
                comentarioRepository, filter, page, size, sort,
                ComentarioFilterFields.ALLOWED_FIELDS);
        return result.map(comentarioMapper::toResponseDto);
    }

    @Override
    public ComentarioResponseDto update(Long id, ComentarioRequestDto requestDto) {
        ComentarioEntity existingEntity = comentarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar ningun comentario con ese id"));

        ComentarioEntity entity = comentarioMapper.toEntity(requestDto);
        entity.setId(id);
        entity.setFecha(existingEntity.getFecha());
        entity.setLikes(entity.getLikes());
        ComentarioEntity savedEntity = comentarioRepository.save(entity);
        return comentarioMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Eliminando comentario: id={}", id);
        if (!comentarioRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ningun comentario con ese id");
        }
        comentarioRepository.deleteById(id);
        log.info("Comentario eliminado exitosamente: id={}", id);
    }
}
