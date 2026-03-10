package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dsl.filters.PlacaBaseFilterFields;
import com.juanCarlos.hardwareHub.dsl.model.FilterCriteria;
import com.juanCarlos.hardwareHub.dsl.parser.QueryDslParser;
import com.juanCarlos.hardwareHub.dsl.specification.SpecificationBuilder;
import com.juanCarlos.hardwareHub.dsl.util.PageableUtils;
import com.juanCarlos.hardwareHub.dsl.validation.FilterValidator;
import com.juanCarlos.hardwareHub.dto.mappers.PlacaBaseMapper;
import com.juanCarlos.hardwareHub.dto.request.PlacaBaseRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PlacaBaseResponseDto;
import com.juanCarlos.hardwareHub.entity.PlacaBaseEntity;
import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
import com.juanCarlos.hardwareHub.entity.enums.PlacaBaseFormato;
import com.juanCarlos.hardwareHub.entity.enums.RamTipo;
import com.juanCarlos.hardwareHub.repository.PlacaBaseRepository;
import com.juanCarlos.hardwareHub.service.PlacaBaseService;
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
public class PlacaBaseServiceImplementation implements PlacaBaseService {

    private final PlacaBaseRepository placaBaseRepository;
    private final PlacaBaseMapper placaBaseMapper;
    private final QueryDslParser parser = new QueryDslParser();

    @Override
    public PlacaBaseResponseDto create(PlacaBaseRequestDto requestDto) {
        PlacaBaseEntity entity = placaBaseMapper.toEntity(requestDto);
        PlacaBaseEntity savedEntity = placaBaseRepository.save(entity);
        return placaBaseMapper.toResponseDto(savedEntity);
    }

    @Override
    public PlacaBaseResponseDto getById(Long id) {
        PlacaBaseEntity entity = placaBaseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ninguna placa base con ese id"));
        return placaBaseMapper.toResponseDto(entity);
    }

    @Override
    public Page<PlacaBaseResponseDto> searchAll(String filter, int page, int size, String sort) {
        List<FilterCriteria> filters = parser.parse(filter);

        FilterValidator.validate(filters, PlacaBaseFilterFields.ALLOWED_FIELDS);

        Specification<PlacaBaseEntity> spec = new SpecificationBuilder<PlacaBaseEntity>().build(filters);

        Pageable pageable = PageableUtils.createPageable(page, size, sort);

        Page<PlacaBaseEntity> result = placaBaseRepository.findAll(spec, pageable);

        return result.map(placaBaseMapper::toResponseDto);
    }

    @Override
    public PlacaBaseResponseDto update(Long id, PlacaBaseRequestDto requestDto) {
        if (!placaBaseRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna placa base con ese id");
        }

        PlacaBaseEntity entity = placaBaseMapper.toEntity(requestDto);
        entity.setId(id);
        PlacaBaseEntity savedEntity = placaBaseRepository.save(entity);
        return placaBaseMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!placaBaseRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna placa base con ese id");
        }
        placaBaseRepository.deleteById(id);
    }
}
