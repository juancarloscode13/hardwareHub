package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dsl.filters.GpuFilterFields;
import com.juanCarlos.hardwareHub.dsl.search.GenericSearchService;
import com.juanCarlos.hardwareHub.dto.mappers.GpuMapper;
import com.juanCarlos.hardwareHub.dto.request.GpuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.GpuResponseDto;
import com.juanCarlos.hardwareHub.entity.GpuEntity;
import com.juanCarlos.hardwareHub.repository.GpuRepository;
import com.juanCarlos.hardwareHub.service.GpuService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class GpuServiceImplementation implements GpuService {

    private final GpuRepository gpuRepository;
    private final GpuMapper gpuMapper;
    private final GenericSearchService searchService;

    @Override
    public GpuResponseDto create(GpuRequestDto requestDto) {
        GpuEntity entity = gpuMapper.toEntity(requestDto);
        GpuEntity savedEntity = gpuRepository.save(entity);
        return gpuMapper.toResponseDto(savedEntity);
    }

    @Override
    public GpuResponseDto getById(Long id) {
        GpuEntity entity = gpuRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ninguna gpu con ese id"));
        return gpuMapper.toResponseDto(entity);
    }

    @Override
    public Page<GpuResponseDto> searchAll(String filter, int page, int size, String sort) {
        Page<GpuEntity> result = searchService.search(
                gpuRepository, filter, page, size, sort,
                GpuFilterFields.ALLOWED_FIELDS);
        return result.map(gpuMapper::toResponseDto);
    }

    @Override
    public GpuResponseDto update(Long id, GpuRequestDto requestDto) {
        if (!gpuRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna gpu con ese id");
        }

        GpuEntity entity = gpuMapper.toEntity(requestDto);
        entity.setId(id);
        GpuEntity savedEntity = gpuRepository.save(entity);
        return gpuMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!gpuRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna gpu con ese id");
        }
        gpuRepository.deleteById(id);
    }
}
