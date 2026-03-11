package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dsl.filters.CajaFilterFields;
import com.juanCarlos.hardwareHub.dsl.search.GenericSearchService;
import com.juanCarlos.hardwareHub.dto.mappers.CajaMapper;
import com.juanCarlos.hardwareHub.dto.request.CajaRequestDto;
import com.juanCarlos.hardwareHub.dto.response.CajaResponseDto;
import com.juanCarlos.hardwareHub.entity.CajaEntity;
import com.juanCarlos.hardwareHub.repository.CajaRepository;
import com.juanCarlos.hardwareHub.service.CajaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class CajaServiceImplementation implements CajaService {

    private final CajaRepository cajaRepository;
    private final CajaMapper cajaMapper;
    private final GenericSearchService searchService;

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
        Page<CajaEntity> result = searchService.search(
                cajaRepository, filter, page, size, sort,
                CajaFilterFields.ALLOWED_FIELDS);
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
