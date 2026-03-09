package com.juanCarlos.hardwareHub.service.implementation;

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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class CpuServiceImplementation implements CpuService {

    private final CpuRepository cpuRepository;
    private final CpuMapper cpuMapper;

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
    public List<CpuResponseDto> getAll() {
        List<CpuEntity> entities = cpuRepository.findAll();
        return cpuMapper.toResponseDtoList(entities);
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

    @Override
    public List<CpuResponseDto> getByCpuSocket(CpuSocket cpuSocket) {
        List<CpuEntity> entities = cpuRepository.getByCpuSocket(cpuSocket);
        return cpuMapper.toResponseDtoList(entities);
    }

    @Override
    public List<CpuResponseDto> getByConectividadPcie(Integer conectividadPcie) {
        List<CpuEntity> entities = cpuRepository.getByConectividadPcie(conectividadPcie);
        return cpuMapper.toResponseDtoList(entities);
    }

    @Override
    public List<CpuResponseDto> getByPuntuacionPassmarkGreaterThanEqual(Integer puntuacionPassmark) {
        List<CpuEntity> entities = cpuRepository.getByPuntuacionPassmarkGreaterThanEqual(puntuacionPassmark);
        return cpuMapper.toResponseDtoList(entities);
    }

    @Override
    public List<CpuResponseDto> getAllOrderByPuntuacionPassmarkDesc() {
        List<CpuEntity> entities = cpuRepository.findAllByOrderByPuntuacionPassmarkDesc();
        return cpuMapper.toResponseDtoList(entities);
    }
}
