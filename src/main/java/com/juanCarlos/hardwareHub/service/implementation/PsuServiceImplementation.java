package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dto.mappers.PsuMapper;
import com.juanCarlos.hardwareHub.dto.request.PsuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PsuResponseDto;
import com.juanCarlos.hardwareHub.entity.PsuEntity;
import com.juanCarlos.hardwareHub.entity.enums.PsuFactorForma;
import com.juanCarlos.hardwareHub.repository.PsuRepository;
import com.juanCarlos.hardwareHub.service.PsuService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class PsuServiceImplementation implements PsuService {

    private final PsuRepository psuRepository;
    private final PsuMapper psuMapper;

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
    public List<PsuResponseDto> getAll() {
        List<PsuEntity> entities = psuRepository.findAll();
        return psuMapper.toResponseDtoList(entities);
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

    @Override
    public List<PsuResponseDto> getByFactorForma(PsuFactorForma factorForma) {
        List<PsuEntity> entities = psuRepository.getByFactorForma(factorForma);
        return psuMapper.toResponseDtoList(entities);
    }

    @Override
    public List<PsuResponseDto> getByPotenciaGreaterThanEqual(Integer potencia) {
        List<PsuEntity> entities = psuRepository.getByPotenciaGreaterThanEqual(potencia);
        return psuMapper.toResponseDtoList(entities);
    }
}
