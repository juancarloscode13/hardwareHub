package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dsl.filters.MontajeFilterFields;
import com.juanCarlos.hardwareHub.dsl.model.FilterCriteria;
import com.juanCarlos.hardwareHub.dsl.parser.QueryDslParser;
import com.juanCarlos.hardwareHub.dsl.specification.SpecificationBuilder;
import com.juanCarlos.hardwareHub.dsl.util.PageableUtils;
import com.juanCarlos.hardwareHub.dsl.validation.FilterValidator;
import com.juanCarlos.hardwareHub.dto.mappers.MontajeMapper;
import com.juanCarlos.hardwareHub.dto.request.MontajeRequestDto;
import com.juanCarlos.hardwareHub.dto.response.MontajeResponseDto;
import com.juanCarlos.hardwareHub.entity.MontajeEntity;
import com.juanCarlos.hardwareHub.repository.MontajeRepository;
import com.juanCarlos.hardwareHub.service.MontajeService;
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
public class MontajeServiceImplementation implements MontajeService {

    private final MontajeRepository montajeRepository;
    private final MontajeMapper montajeMapper;
    private final QueryDslParser parser = new QueryDslParser();

    @Override
    public MontajeResponseDto create(MontajeRequestDto requestDto) {
        MontajeEntity entity = montajeMapper.toEntity(requestDto);
        MontajeEntity savedEntity = montajeRepository.save(entity);
        return montajeMapper.toResponseDto(savedEntity);
    }

    @Override
    public MontajeResponseDto getById(Long id) {
        MontajeEntity entity = montajeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ningun montaje con ese id"));
        return montajeMapper.toResponseDto(entity);
    }

    @Override
    public Page<MontajeResponseDto> searchAll(String filter, int page, int size, String sort) {
        List<FilterCriteria> filters = parser.parse(filter);

        FilterValidator.validate(filters, MontajeFilterFields.ALLOWED_FIELDS);

        Specification<MontajeEntity> spec = new SpecificationBuilder<MontajeEntity>().build(filters);

        Pageable pageable = PageableUtils.createPageable(page, size, sort);

        Page<MontajeEntity> result = montajeRepository.findAll(spec, pageable);

        return result.map(montajeMapper::toResponseDto);
    }

    @Override
    public MontajeResponseDto update(Long id, MontajeRequestDto requestDto) {
        if (!montajeRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ningun montaje con ese id");
        }

        MontajeEntity entity = montajeMapper.toEntity(requestDto);
        entity.setId(id);
        MontajeEntity savedEntity = montajeRepository.save(entity);
        return montajeMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!montajeRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ningun montaje con ese id");
        }
        montajeRepository.deleteById(id);
    }

}
