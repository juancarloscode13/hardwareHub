package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dsl.filters.CpuFilterFields;
import com.juanCarlos.hardwareHub.dsl.model.FilterCriteria;
import com.juanCarlos.hardwareHub.dsl.parser.QueryDslParser;
import com.juanCarlos.hardwareHub.dsl.specification.SpecificationBuilder;
import com.juanCarlos.hardwareHub.dsl.util.PageableUtils;
import com.juanCarlos.hardwareHub.dsl.validation.FilterValidator;
import com.juanCarlos.hardwareHub.dto.mappers.CpuMapper;
import com.juanCarlos.hardwareHub.dto.request.CpuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.CpuResponseDto;
import com.juanCarlos.hardwareHub.entity.CpuEntity;
import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
import com.juanCarlos.hardwareHub.repository.CpuRepository;
import com.juanCarlos.hardwareHub.service.CpuService;
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
public class CpuServiceImplementation implements CpuService {

    private final CpuRepository cpuRepository;
    private final CpuMapper cpuMapper;
    private final QueryDslParser parser = new QueryDslParser();

    @Override
    public CpuResponseDto create(CpuRequestDto requestDto) {
        CpuEntity entity = cpuMapper.toEntity(requestDto);
        CpuEntity savedEntity = cpuRepository.save(entity);
        return cpuMapper.toResponseDto(savedEntity);
    }

    @Override
    public CpuResponseDto getById(Long id) {
        CpuEntity entity = cpuRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ninguna cpu con ese id"));
        return cpuMapper.toResponseDto(entity);
    }

    @Override
    public Page<CpuResponseDto> searchAll(String filter, int page, int size, String sort) {
        List<FilterCriteria> filters = parser.parse(filter);

        FilterValidator.validate(filters, CpuFilterFields.ALLOWED_FIELDS);

        Specification<CpuEntity> spec = new SpecificationBuilder<CpuEntity>().build(filters);

        Pageable pageable = PageableUtils.createPageable(page, size, sort);

        Page<CpuEntity> result = cpuRepository.findAll(spec, pageable);

        return result.map(cpuMapper::toResponseDto);
    }

    @Override
    public CpuResponseDto update(Long id, CpuRequestDto requestDto) {
        if (!cpuRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna cpu con ese id");
        }

        CpuEntity entity = cpuMapper.toEntity(requestDto);
        entity.setId(id);
        CpuEntity savedEntity = cpuRepository.save(entity);
        return cpuMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!cpuRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna cpu con ese id");
        }
        cpuRepository.deleteById(id);
    }
}
