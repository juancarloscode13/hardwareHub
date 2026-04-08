package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dsl.filters.AlmacenamientoFilterFields;
import com.juanCarlos.hardwareHub.dsl.search.GenericSearchService;
import com.juanCarlos.hardwareHub.dto.mappers.AlmacenamientoMapper;
import com.juanCarlos.hardwareHub.dto.request.AlmacenamientoRequestDto;
import com.juanCarlos.hardwareHub.dto.response.AlmacenamientoResponseDto;
import com.juanCarlos.hardwareHub.entity.AlmacenamientoEntity;
import com.juanCarlos.hardwareHub.repository.AlmacenamientoRepository;
import com.juanCarlos.hardwareHub.service.AlmacenamientoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * Implementacion del servicio de la entidad AlmacenamientoEntity
 *
 * @see AlmacenamientoService
 * @author Juan Carlos
 */

@Service
@Transactional
@AllArgsConstructor
public class AlmacenamientoServiceImplementation implements AlmacenamientoService {

    private final AlmacenamientoRepository almacenamientoRepository;
    private final AlmacenamientoMapper almacenamientoMapper;
    private final GenericSearchService searchService;

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
    public Page<AlmacenamientoResponseDto> searchAll(String filter, int page, int size, String sort) {
        Page<AlmacenamientoEntity> result = searchService.search(
                almacenamientoRepository, filter, page, size, sort,
                AlmacenamientoFilterFields.ALLOWED_FIELDS);
        return result.map(almacenamientoMapper::toResponseDto);
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
}
