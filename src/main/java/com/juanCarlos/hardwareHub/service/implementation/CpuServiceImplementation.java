package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dsl.filters.CpuFilterFields;
import com.juanCarlos.hardwareHub.dsl.search.GenericSearchService;
import com.juanCarlos.hardwareHub.dto.mappers.CpuMapper;
import com.juanCarlos.hardwareHub.dto.request.CpuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.CpuResponseDto;
import com.juanCarlos.hardwareHub.entity.CpuEntity;
import com.juanCarlos.hardwareHub.repository.CpuRepository;
import com.juanCarlos.hardwareHub.service.CpuService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class CpuServiceImplementation implements CpuService {

    private final CpuRepository cpuRepository;
    private final CpuMapper cpuMapper;
    private final GenericSearchService searchService;

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
        Page<CpuEntity> result = searchService.search(
                cpuRepository, filter, page, size, sort,
                CpuFilterFields.ALLOWED_FIELDS);
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
