package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dto.mappers.GpuMapper;
import com.juanCarlos.hardwareHub.dto.request.GpuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.GpuResponseDto;
import com.juanCarlos.hardwareHub.entity.GpuEntity;
import com.juanCarlos.hardwareHub.repository.GpuRepository;
import com.juanCarlos.hardwareHub.service.GpuService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class GpuServiceImplementation implements GpuService {

    private final GpuRepository gpuRepository;
    private final GpuMapper gpuMapper;

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
    public List<GpuResponseDto> getAll() {
        List<GpuEntity> entities = gpuRepository.findAll();
        return gpuMapper.toResponseDtoList(entities);
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

    @Override
    public List<GpuResponseDto> getByConectividadPcie(Integer conectividadPcie) {
        List<GpuEntity> entities = gpuRepository.getByConectividadPcie(conectividadPcie);
        return gpuMapper.toResponseDtoList(entities);
    }

    @Override
    public List<GpuResponseDto> getByAltoGpuLessThanEqual(Integer altoGpu) {
        List<GpuEntity> entities = gpuRepository.getByAltoGpuLessThanEqual(altoGpu);
        return gpuMapper.toResponseDtoList(entities);
    }

    @Override
    public List<GpuResponseDto> getByPuntuacionPassmarkGreaterThanEqual(Integer puntuacionPassmark) {
        List<GpuEntity> entities = gpuRepository.getByPuntuacionPassmarkGreaterThanEqual(puntuacionPassmark);
        return gpuMapper.toResponseDtoList(entities);
    }

    @Override
    public List<GpuResponseDto> getAllOrderByPuntuacionPassmarkDesc() {
        List<GpuEntity> entities = gpuRepository.findAllByOrderByPuntuacionPassmarkDesc();
        return gpuMapper.toResponseDtoList(entities);
    }
}
