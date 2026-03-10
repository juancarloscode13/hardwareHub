package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dsl.filters.PublicacionFilterFields;
import com.juanCarlos.hardwareHub.dsl.model.FilterCriteria;
import com.juanCarlos.hardwareHub.dsl.parser.QueryDslParser;
import com.juanCarlos.hardwareHub.dsl.specification.SpecificationBuilder;
import com.juanCarlos.hardwareHub.dsl.util.PageableUtils;
import com.juanCarlos.hardwareHub.dsl.validation.FilterValidator;
import com.juanCarlos.hardwareHub.dto.mappers.PublicacionMapper;
import com.juanCarlos.hardwareHub.dto.request.PublicacionRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PublicacionResponseDto;
import com.juanCarlos.hardwareHub.entity.PublicacionEntity;
import com.juanCarlos.hardwareHub.repository.PublicacionRepository;
import com.juanCarlos.hardwareHub.service.PublicacionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class PublicacionServiceImplementation implements PublicacionService {

    private final PublicacionRepository publicacionRepository;
    private final PublicacionMapper publicacionMapper;
    private final QueryDslParser parser = new QueryDslParser();

    @Override
    public PublicacionResponseDto create(PublicacionRequestDto requestDto) {
        PublicacionEntity entity = publicacionMapper.toEntity(requestDto);
        validateMontajeMultimediaRule(entity);
        if (entity.getLikes() == null) {
            entity.setLikes(0);
        }
        entity.setFecha(LocalDateTime.now());
        PublicacionEntity savedEntity = publicacionRepository.save(entity);
        return publicacionMapper.toResponseDto(savedEntity);
    }

    @Override
    public PublicacionResponseDto getById(Long id) {
        PublicacionEntity entity = publicacionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ninguna publicacion con ese id"));
        return publicacionMapper.toResponseDto(entity);
    }

    @Override
    public Page<PublicacionResponseDto> searchAll(String filter, int page, int size, String sort) {
        List<FilterCriteria> filters = parser.parse(filter);

        FilterValidator.validate(filters, PublicacionFilterFields.ALLOWED_FIELDS);

        Specification<PublicacionEntity> spec = new SpecificationBuilder<PublicacionEntity>().build(filters);

        Pageable pageable = PageableUtils.createPageable(page, size, sort);

        Page<PublicacionEntity> result = publicacionRepository.findAll(spec, pageable);

        return result.map(publicacionMapper::toResponseDto);
    }

    @Override
    public PublicacionResponseDto update(Long id, PublicacionRequestDto requestDto) {
        PublicacionEntity existingEntity = publicacionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar ninguna publicacion con ese id"));

        PublicacionEntity entity = publicacionMapper.toEntity(requestDto);
        entity.setId(id);
        entity.setFecha(existingEntity.getFecha());
        entity.setLikes(entity.getLikes() == null ? existingEntity.getLikes() : entity.getLikes());
        validateMontajeMultimediaRule(entity);
        PublicacionEntity savedEntity = publicacionRepository.save(entity);
        return publicacionMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!publicacionRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna publicacion con ese id");
        }
        publicacionRepository.deleteById(id);
    }

    private void validateMontajeMultimediaRule(PublicacionEntity entity) {
        boolean hasMontaje = entity.getMontaje() != null;
        boolean hasMultimedia = entity.getMultimedia() != null;

        if (hasMontaje && hasMultimedia) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Una publicacion no puede tener montaje y multimedia al mismo tiempo"
            );
        }
    }
}
