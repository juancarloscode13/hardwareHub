package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dsl.filters.PsuFilterFields;
import com.juanCarlos.hardwareHub.dsl.search.GenericSearchService;
import com.juanCarlos.hardwareHub.dto.mappers.PsuMapper;
import com.juanCarlos.hardwareHub.dto.request.PsuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PsuResponseDto;
import com.juanCarlos.hardwareHub.entity.PsuEntity;
import com.juanCarlos.hardwareHub.repository.PsuRepository;
import com.juanCarlos.hardwareHub.service.PsuService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class PsuServiceImplementation implements PsuService {

    private final PsuRepository psuRepository;
    private final PsuMapper psuMapper;
    private final GenericSearchService searchService;

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
        Page<PsuEntity> result = searchService.search(
                psuRepository, filter, page, size, sort,
                PsuFilterFields.ALLOWED_FIELDS);
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
