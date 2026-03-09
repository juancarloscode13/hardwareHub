package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dto.mappers.PlacaBaseMapper;
import com.juanCarlos.hardwareHub.dto.request.PlacaBaseRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PlacaBaseResponseDto;
import com.juanCarlos.hardwareHub.entity.PlacaBaseEntity;
import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
import com.juanCarlos.hardwareHub.entity.enums.PlacaBaseFormato;
import com.juanCarlos.hardwareHub.entity.enums.RamTipo;
import com.juanCarlos.hardwareHub.repository.PlacaBaseRepository;
import com.juanCarlos.hardwareHub.service.PlacaBaseService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class PlacaBaseServiceImplementation implements PlacaBaseService {

    private final PlacaBaseRepository placaBaseRepository;
    private final PlacaBaseMapper placaBaseMapper;

    @Override
    public PlacaBaseResponseDto create(PlacaBaseRequestDto requestDto) {
        PlacaBaseEntity entity = placaBaseMapper.toEntity(requestDto);
        PlacaBaseEntity savedEntity = placaBaseRepository.save(entity);
        return placaBaseMapper.toResponseDto(savedEntity);
    }

    @Override
    public PlacaBaseResponseDto getById(Long id) {
        PlacaBaseEntity entity = placaBaseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ninguna placa base con ese id"));
        return placaBaseMapper.toResponseDto(entity);
    }

    @Override
    public List<PlacaBaseResponseDto> getAll() {
        List<PlacaBaseEntity> entities = placaBaseRepository.findAll();
        return placaBaseMapper.toResponseDtoList(entities);
    }

    @Override
    public PlacaBaseResponseDto update(Long id, PlacaBaseRequestDto requestDto) {
        if (!placaBaseRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna placa base con ese id");
        }

        PlacaBaseEntity entity = placaBaseMapper.toEntity(requestDto);
        entity.setId(id);
        PlacaBaseEntity savedEntity = placaBaseRepository.save(entity);
        return placaBaseMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!placaBaseRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna placa base con ese id");
        }
        placaBaseRepository.deleteById(id);
    }

    @Override
    public List<PlacaBaseResponseDto> getBySocketCompatible(CpuSocket socketCompatible) {
        List<PlacaBaseEntity> entities = placaBaseRepository.getBySocketCompatible(socketCompatible);
        return placaBaseMapper.toResponseDtoList(entities);
    }

    @Override
    public List<PlacaBaseResponseDto> getByTipoRamSoportada(RamTipo tipoRamSoportada) {
        List<PlacaBaseEntity> entities = placaBaseRepository.getByTipoRamSoportada(tipoRamSoportada);
        return placaBaseMapper.toResponseDtoList(entities);
    }

    @Override
    public List<PlacaBaseResponseDto> getByFormato(PlacaBaseFormato formato) {
        List<PlacaBaseEntity> entities = placaBaseRepository.getByFormato(formato);
        return placaBaseMapper.toResponseDtoList(entities);
    }
}
