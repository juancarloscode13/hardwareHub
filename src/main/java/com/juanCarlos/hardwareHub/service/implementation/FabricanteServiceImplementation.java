package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dto.mappers.FabricanteMapper;
import com.juanCarlos.hardwareHub.dto.request.FabricanteRequestDto;
import com.juanCarlos.hardwareHub.dto.response.FabricanteResponseDto;
import com.juanCarlos.hardwareHub.entity.FabricanteEntity;
import com.juanCarlos.hardwareHub.repository.FabricanteRepository;
import com.juanCarlos.hardwareHub.service.FabricanteService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class FabricanteServiceImplementation implements FabricanteService {

    private final FabricanteRepository fabricanteRepository;
    private final FabricanteMapper fabricanteMapper;

    @Override
    public FabricanteResponseDto create(FabricanteRequestDto requestDto) {
        FabricanteEntity entity = fabricanteMapper.toEntity(requestDto);
        FabricanteEntity savedEntity = fabricanteRepository.save(entity);
        return fabricanteMapper.toResponseDto(savedEntity);
    }

    @Override
    public FabricanteResponseDto getById(Long id) {
        FabricanteEntity entity = fabricanteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ningun fabricante con ese id"));
        return fabricanteMapper.toResponseDto(entity);
    }

    @Override
    public List<FabricanteResponseDto> getAll() {
        List<FabricanteEntity> entities = fabricanteRepository.findAll();
        return fabricanteMapper.toResponseDtoList(entities);
    }

    @Override
    public FabricanteResponseDto update(Long id, FabricanteRequestDto requestDto) {
        if (!fabricanteRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ningun fabricante con ese id");
        }

        FabricanteEntity entity = fabricanteMapper.toEntity(requestDto);
        entity.setId(id);
        FabricanteEntity savedEntity = fabricanteRepository.save(entity);
        return fabricanteMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!fabricanteRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ningun fabricante con ese id");
        }
        fabricanteRepository.deleteById(id);
    }
}
