package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dto.mappers.AlmacenamientoMapper;
import com.juanCarlos.hardwareHub.dto.request.AlmacenamientoRequestDto;
import com.juanCarlos.hardwareHub.dto.response.AlmacenamientoResponseDto;
import com.juanCarlos.hardwareHub.entity.AlmacenamientoEntity;
import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoConectividad;
import com.juanCarlos.hardwareHub.entity.enums.AlmacenamientoFormato;
import com.juanCarlos.hardwareHub.repository.AlmacenamientoRepository;
import com.juanCarlos.hardwareHub.service.AlmacenamientoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class AlmacenamientoServiceImplementation implements AlmacenamientoService {

    private final AlmacenamientoRepository almacenamientoRepository;
    private final AlmacenamientoMapper almacenamientoMapper;

    @Override
    public AlmacenamientoResponseDto create(AlmacenamientoRequestDto requestDto) {
        AlmacenamientoEntity entity = almacenamientoMapper.toEntity(requestDto);
        AlmacenamientoEntity savedEntity = almacenamientoRepository.save(entity);
        return almacenamientoMapper.toResponseDto(savedEntity);
    }

    @Override
    public AlmacenamientoResponseDto getById(Long id) {
        AlmacenamientoEntity entity = almacenamientoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ningun almacenamiento con ese id"));
        return almacenamientoMapper.toResponseDto(entity);
    }

    @Override
    public List<AlmacenamientoResponseDto> getAll() {
        List<AlmacenamientoEntity> entities = almacenamientoRepository.findAll();
        return almacenamientoMapper.toResponseDtoList(entities);
    }

    @Override
    public AlmacenamientoResponseDto update(Long id, AlmacenamientoRequestDto requestDto) {
        if (!almacenamientoRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ningun almacenamiento con ese id");
        }

        AlmacenamientoEntity entity = almacenamientoMapper.toEntity(requestDto);
        entity.setId(id);
        AlmacenamientoEntity savedEntity = almacenamientoRepository.save(entity);
        return almacenamientoMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!almacenamientoRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ningun almacenamiento con ese id");
        }
        almacenamientoRepository.deleteById(id);
    }

    @Override
    public List<AlmacenamientoResponseDto> getByConectividad(AlmacenamientoConectividad conectividad) {
        List<AlmacenamientoEntity> entities = almacenamientoRepository.getByConectividad(conectividad);
        return almacenamientoMapper.toResponseDtoList(entities);
    }

    @Override
    public List<AlmacenamientoResponseDto> getByFormato(AlmacenamientoFormato formato) {
        List<AlmacenamientoEntity> entities = almacenamientoRepository.getByFormato(formato);
        return almacenamientoMapper.toResponseDtoList(entities);
    }
}
