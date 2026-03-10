package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dsl.filters.AlmacenamientoFilterFields;
import com.juanCarlos.hardwareHub.dsl.model.FilterCriteria;
import com.juanCarlos.hardwareHub.dsl.parser.QueryDslParser;
import com.juanCarlos.hardwareHub.dsl.specification.SpecificationBuilder;
import com.juanCarlos.hardwareHub.dsl.util.PageableUtils;
import com.juanCarlos.hardwareHub.dsl.validation.FilterValidator;
import com.juanCarlos.hardwareHub.dto.mappers.AlmacenamientoMapper;
import com.juanCarlos.hardwareHub.dto.request.AlmacenamientoRequestDto;
import com.juanCarlos.hardwareHub.dto.response.AlmacenamientoResponseDto;
import com.juanCarlos.hardwareHub.entity.AlmacenamientoEntity;
import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoConectividad;
import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoFormato;
import com.juanCarlos.hardwareHub.repository.AlmacenamientoRepository;
import com.juanCarlos.hardwareHub.service.AlmacenamientoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class AlmacenamientoServiceImplementation implements AlmacenamientoService {

    private final AlmacenamientoRepository almacenamientoRepository;
    private final AlmacenamientoMapper almacenamientoMapper;
    private final QueryDslParser parser = new QueryDslParser();

    @Override
    public AlmacenamientoResponseDto create(AlmacenamientoRequestDto requestDto) {
        AlmacenamientoEntity entity = almacenamientoMapper.toEntity(requestDto);
        AlmacenamientoEntity savedEntity = almacenamientoRepository.save(entity);
        return almacenamientoMapper.toResponseDto(savedEntity);
    }

    @Override
    public AlmacenamientoResponseDto getById(Long id) {
        AlmacenamientoEntity entity = almacenamientoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ningun almacenamiento con ese id"));
        return almacenamientoMapper.toResponseDto(entity);
    }

    @Override
    public Page<AlmacenamientoResponseDto> searchAll(String filter, int page, int size, String sort) {
        List<FilterCriteria> filters = parser.parse(filter);

        FilterValidator.validate(filters, AlmacenamientoFilterFields.ALLOWED_FIELDS);

        Specification<AlmacenamientoEntity> spec = new SpecificationBuilder<AlmacenamientoEntity>().build(filters);

        Pageable pageable = PageableUtils.createPageable(page, size, sort);

        Page<AlmacenamientoEntity> result = almacenamientoRepository.findAll(spec, pageable);

        return result.map(almacenamientoMapper::toResponseDto);
    }

    @Override
    public AlmacenamientoResponseDto update(Long id, AlmacenamientoRequestDto requestDto) {
        if (!almacenamientoRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ningun almacenamiento con ese id");
        }

        AlmacenamientoEntity entity = almacenamientoMapper.toEntity(requestDto);
        entity.setId(id);
        AlmacenamientoEntity savedEntity = almacenamientoRepository.save(entity);
        return almacenamientoMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!almacenamientoRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ningun almacenamiento con ese id");
        }
        almacenamientoRepository.deleteById(id);
    }
}
