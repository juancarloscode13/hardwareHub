package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dsl.filters.CajaFilterFields;
import com.juanCarlos.hardwareHub.dsl.model.FilterCriteria;
import com.juanCarlos.hardwareHub.dsl.parser.QueryDslParser;
import com.juanCarlos.hardwareHub.dsl.specification.SpecificationBuilder;
import com.juanCarlos.hardwareHub.dsl.util.PageableUtils;
import com.juanCarlos.hardwareHub.dsl.validation.FilterValidator;
import com.juanCarlos.hardwareHub.dto.mappers.CajaMapper;
import com.juanCarlos.hardwareHub.dto.request.CajaRequestDto;
import com.juanCarlos.hardwareHub.dto.response.CajaResponseDto;
import com.juanCarlos.hardwareHub.entity.CajaEntity;
import com.juanCarlos.hardwareHub.entity.enums.CajaFormato;
import com.juanCarlos.hardwareHub.entity.enums.PsuFactorForma;
import com.juanCarlos.hardwareHub.repository.CajaRepository;
import com.juanCarlos.hardwareHub.service.CajaService;
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
public class CajaServiceImplementation implements CajaService {

    private final CajaRepository cajaRepository;
    private final CajaMapper cajaMapper;
    private final QueryDslParser parser = new QueryDslParser();

    @Override
    public CajaResponseDto create(CajaRequestDto requestDto) {
        CajaEntity entity = cajaMapper.toEntity(requestDto);
        CajaEntity savedEntity = cajaRepository.save(entity);
        return cajaMapper.toResponseDto(savedEntity);
    }

    @Override
    public CajaResponseDto getById(Long id) {
        CajaEntity entity = cajaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ninguna caja con ese id"));
        return cajaMapper.toResponseDto(entity);
    }

    @Override
    public Page<CajaResponseDto> searchAll(String filter, int page, int size, String sort) {
        List<FilterCriteria> filters = parser.parse(filter);

        FilterValidator.validate(filters, CajaFilterFields.ALLOWED_FIELDS);

        Specification<CajaEntity> spec = new SpecificationBuilder<CajaEntity>().build(filters);

        Pageable pageable = PageableUtils.createPageable(page, size, sort);

        Page<CajaEntity> result = cajaRepository.findAll(spec, pageable);

        return result.map(cajaMapper::toResponseDto);
    }

    @Override
    public CajaResponseDto update(Long id, CajaRequestDto requestDto) {
        if (!cajaRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna caja con ese id");
        }

        CajaEntity entity = cajaMapper.toEntity(requestDto);
        entity.setId(id);
        CajaEntity savedEntity = cajaRepository.save(entity);
        return cajaMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!cajaRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna caja con ese id");
        }
        cajaRepository.deleteById(id);
    }
}
