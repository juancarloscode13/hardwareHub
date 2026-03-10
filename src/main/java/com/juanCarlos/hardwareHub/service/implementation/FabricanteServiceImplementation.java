package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dsl.filters.FabricanteFilterFields;
import com.juanCarlos.hardwareHub.dsl.model.FilterCriteria;
import com.juanCarlos.hardwareHub.dsl.parser.QueryDslParser;
import com.juanCarlos.hardwareHub.dsl.specification.SpecificationBuilder;
import com.juanCarlos.hardwareHub.dsl.util.PageableUtils;
import com.juanCarlos.hardwareHub.dsl.validation.FilterValidator;
import com.juanCarlos.hardwareHub.dto.mappers.FabricanteMapper;
import com.juanCarlos.hardwareHub.dto.request.FabricanteRequestDto;
import com.juanCarlos.hardwareHub.dto.response.FabricanteResponseDto;
import com.juanCarlos.hardwareHub.entity.FabricanteEntity;
import com.juanCarlos.hardwareHub.repository.FabricanteRepository;
import com.juanCarlos.hardwareHub.service.FabricanteService;
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
public class FabricanteServiceImplementation implements FabricanteService {

    private final FabricanteRepository fabricanteRepository;
    private final FabricanteMapper fabricanteMapper;
    private final QueryDslParser parser = new QueryDslParser();

    @Override
    public FabricanteResponseDto create(FabricanteRequestDto requestDto) {
        FabricanteEntity entity = fabricanteMapper.toEntity(requestDto);
        FabricanteEntity savedEntity = fabricanteRepository.save(entity);
        return fabricanteMapper.toResponseDto(savedEntity);
    }

    @Override
    public FabricanteResponseDto getById(Long id) {
        FabricanteEntity entity = fabricanteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ningun fabricante con ese id"));
        return fabricanteMapper.toResponseDto(entity);
    }

    @Override
    public Page<FabricanteResponseDto> searchAll(String filter, int page, int size, String sort) {
        List<FilterCriteria> filters = parser.parse(filter);

        FilterValidator.validate(filters, FabricanteFilterFields.ALLOWED_FIELDS);

        Specification<FabricanteEntity> spec = new SpecificationBuilder<FabricanteEntity>().build(filters);

        Pageable pageable = PageableUtils.createPageable(page, size, sort);

        Page<FabricanteEntity> result = fabricanteRepository.findAll(spec, pageable);

        return result.map(fabricanteMapper::toResponseDto);
    }

    @Override
    public FabricanteResponseDto update(Long id, FabricanteRequestDto requestDto) {
        if (!fabricanteRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ningun fabricante con ese id");
        }

        FabricanteEntity entity = fabricanteMapper.toEntity(requestDto);
        entity.setId(id);
        FabricanteEntity savedEntity = fabricanteRepository.save(entity);
        return fabricanteMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!fabricanteRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ningun fabricante con ese id");
        }
        fabricanteRepository.deleteById(id);
    }
}
