package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dsl.filters.RamFilterFields;
import com.juanCarlos.hardwareHub.dsl.model.FilterCriteria;
import com.juanCarlos.hardwareHub.dsl.parser.QueryDslParser;
import com.juanCarlos.hardwareHub.dsl.specification.SpecificationBuilder;
import com.juanCarlos.hardwareHub.dsl.util.PageableUtils;
import com.juanCarlos.hardwareHub.dsl.validation.FilterValidator;
import com.juanCarlos.hardwareHub.dto.mappers.RamMapper;
import com.juanCarlos.hardwareHub.dto.request.RamRequestDto;
import com.juanCarlos.hardwareHub.dto.response.RamResponseDto;
import com.juanCarlos.hardwareHub.entity.RamEntity;
import com.juanCarlos.hardwareHub.entity.enums.RamTipo;
import com.juanCarlos.hardwareHub.repository.RamRepository;
import com.juanCarlos.hardwareHub.service.RamService;
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
public class RamServiceImplementation implements RamService {

    private final RamRepository ramRepository;
    private final RamMapper ramMapper;
    private final QueryDslParser parser = new QueryDslParser();

    @Override
    public RamResponseDto create(RamRequestDto requestDto) {
        RamEntity entity = ramMapper.toEntity(requestDto);
        RamEntity savedEntity = ramRepository.save(entity);
        return ramMapper.toResponseDto(savedEntity);
    }

    @Override
    public RamResponseDto getById(Long id) {
        RamEntity entity = ramRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ninguna ram con ese id"));
        return ramMapper.toResponseDto(entity);
    }

    @Override
    public Page<RamResponseDto> searchAll(String filter, int page, int size, String sort) {
        List<FilterCriteria> filters = parser.parse(filter);

        FilterValidator.validate(filters, RamFilterFields.ALLOWED_FIELDS);

        Specification<RamEntity> spec = new SpecificationBuilder<RamEntity>().build(filters);

        Pageable pageable = PageableUtils.createPageable(page, size, sort);

        Page<RamEntity> result = ramRepository.findAll(spec, pageable);

        return result.map(ramMapper::toResponseDto);
    }

    @Override
    public RamResponseDto update(Long id, RamRequestDto requestDto) {
        if (!ramRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna ram con ese id");
        }

        RamEntity entity = ramMapper.toEntity(requestDto);
        entity.setId(id);
        RamEntity savedEntity = ramRepository.save(entity);
        return ramMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!ramRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna ram con ese id");
        }
        ramRepository.deleteById(id);
    }
}
