package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dsl.filters.ComentarioFilterFields;
import com.juanCarlos.hardwareHub.dsl.model.FilterCriteria;
import com.juanCarlos.hardwareHub.dsl.parser.QueryDslParser;
import com.juanCarlos.hardwareHub.dsl.specification.SpecificationBuilder;
import com.juanCarlos.hardwareHub.dsl.util.PageableUtils;
import com.juanCarlos.hardwareHub.dsl.validation.FilterValidator;
import com.juanCarlos.hardwareHub.dto.mappers.ComentarioMapper;
import com.juanCarlos.hardwareHub.dto.request.ComentarioRequestDto;
import com.juanCarlos.hardwareHub.dto.response.ComentarioResponseDto;
import com.juanCarlos.hardwareHub.entity.ComentarioEntity;
import com.juanCarlos.hardwareHub.repository.ComentarioRepository;
import com.juanCarlos.hardwareHub.service.ComentarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.time.LocalDateTime;

@Service
@Transactional
@AllArgsConstructor
public class ComentarioServiceImplementation implements ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;
    private final QueryDslParser parser = new QueryDslParser();

    @Override
    public ComentarioResponseDto create(ComentarioRequestDto requestDto) {
        ComentarioEntity entity = comentarioMapper.toEntity(requestDto);
        if (entity.getLikes() == null) {
            entity.setLikes(0);
        }
        entity.setFecha(LocalDateTime.now());
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
    public Page<ComentarioResponseDto> searchAll(String filter, int page, int size, String sort) {
        List<FilterCriteria> filters = parser.parse(filter);

        FilterValidator.validate(filters, ComentarioFilterFields.ALLOWED_FIELDS);

        Specification<ComentarioEntity> spec = new SpecificationBuilder<ComentarioEntity>().build(filters);

        Pageable pageable = PageableUtils.createPageable(page, size, sort);

        Page<ComentarioEntity> result = comentarioRepository.findAll(spec, pageable);

        return result.map(comentarioMapper::toResponseDto);
    }

    @Override
    public ComentarioResponseDto update(Long id, ComentarioRequestDto requestDto) {
        ComentarioEntity existingEntity = comentarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar ningun comentario con ese id"));

        ComentarioEntity entity = comentarioMapper.toEntity(requestDto);
        entity.setId(id);
        entity.setFecha(existingEntity.getFecha());
        entity.setLikes(entity.getLikes() == null ? existingEntity.getLikes() : entity.getLikes());
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
