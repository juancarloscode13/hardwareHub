package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dto.mappers.RamMapper;
import com.juanCarlos.hardwareHub.dto.request.RamRequestDto;
import com.juanCarlos.hardwareHub.dto.response.RamResponseDto;
import com.juanCarlos.hardwareHub.entity.RamEntity;
import com.juanCarlos.hardwareHub.entity.enums.RamTipo;
import com.juanCarlos.hardwareHub.repository.RamRepository;
import com.juanCarlos.hardwareHub.service.RamService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class RamServiceImplementation implements RamService {

    private final RamRepository ramRepository;
    private final RamMapper ramMapper;

    @Override
    public RamResponseDto create(RamRequestDto requestDto) {
        RamEntity entity = ramMapper.toEntity(requestDto);
        RamEntity savedEntity = ramRepository.save(entity);
        return ramMapper.toResponseDto(savedEntity);
    }

    @Override
    public RamResponseDto getById(Long id) {
        RamEntity entity = ramRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ninguna ram con ese id"));
        return ramMapper.toResponseDto(entity);
    }

    @Override
    public List<RamResponseDto> getAll() {
        List<RamEntity> entities = ramRepository.findAll();
        return ramMapper.toResponseDtoList(entities);
    }

    @Override
    public RamResponseDto update(Long id, RamRequestDto requestDto) {
        if (!ramRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna ram con ese id");
        }

        RamEntity entity = ramMapper.toEntity(requestDto);
        entity.setId(id);
        RamEntity savedEntity = ramRepository.save(entity);
        return ramMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!ramRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna ram con ese id");
        }
        ramRepository.deleteById(id);
    }

    @Override
    public List<RamResponseDto> getByTipo(RamTipo tipo) {
        List<RamEntity> entities = ramRepository.getByTipo(tipo);
        return ramMapper.toResponseDtoList(entities);
    }
}
