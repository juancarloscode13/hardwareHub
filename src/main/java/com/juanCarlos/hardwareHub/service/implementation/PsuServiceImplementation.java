package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dsl.filters.PsuFilterFields;
import com.juanCarlos.hardwareHub.dsl.model.FilterCriteria;
import com.juanCarlos.hardwareHub.dsl.parser.QueryDslParser;
import com.juanCarlos.hardwareHub.dsl.specification.SpecificationBuilder;
import com.juanCarlos.hardwareHub.dsl.util.PageableUtils;
import com.juanCarlos.hardwareHub.dsl.validation.FilterValidator;
import com.juanCarlos.hardwareHub.dto.mappers.PsuMapper;
import com.juanCarlos.hardwareHub.dto.request.PsuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PsuResponseDto;
import com.juanCarlos.hardwareHub.entity.PsuEntity;
import com.juanCarlos.hardwareHub.entity.enums.PsuFactorForma;
import com.juanCarlos.hardwareHub.repository.PsuRepository;
import com.juanCarlos.hardwareHub.service.PsuService;
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
public class PsuServiceImplementation implements PsuService {

    private final PsuRepository psuRepository;
    private final PsuMapper psuMapper;
    private final QueryDslParser parser = new QueryDslParser();

    @Override
    public PsuResponseDto create(PsuRequestDto requestDto) {
        PsuEntity entity = psuMapper.toEntity(requestDto);
        PsuEntity savedEntity = psuRepository.save(entity);
        return psuMapper.toResponseDto(savedEntity);
    }

    @Override
    public PsuResponseDto getById(Long id) {
        PsuEntity entity = psuRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ninguna psu con ese id"));
        return psuMapper.toResponseDto(entity);
    }

    @Override
    public Page<PsuResponseDto> searchAll(String filter, int page, int size, String sort) {
        List<FilterCriteria> filters = parser.parse(filter);

        FilterValidator.validate(filters, PsuFilterFields.ALLOWED_FIELDS);

        Specification<PsuEntity> spec = new SpecificationBuilder<PsuEntity>().build(filters);

        Pageable pageable = PageableUtils.createPageable(page, size, sort);

        Page<PsuEntity> result = psuRepository.findAll(spec, pageable);

        return result.map(psuMapper::toResponseDto);
    }

    @Override
    public PsuResponseDto update(Long id, PsuRequestDto requestDto) {
        if (!psuRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna psu con ese id");
        }

        PsuEntity entity = psuMapper.toEntity(requestDto);
        entity.setId(id);
        PsuEntity savedEntity = psuRepository.save(entity);
        return psuMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!psuRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna psu con ese id");
        }
        psuRepository.deleteById(id);
    }
}
