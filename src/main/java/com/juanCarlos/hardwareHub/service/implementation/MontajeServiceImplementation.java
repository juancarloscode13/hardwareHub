package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dsl.filters.MontajeFilterFields;
import com.juanCarlos.hardwareHub.dsl.search.GenericSearchService;
import com.juanCarlos.hardwareHub.dto.mappers.MontajeMapper;
import com.juanCarlos.hardwareHub.dto.request.MontajeRequestDto;
import com.juanCarlos.hardwareHub.dto.response.MontajeResponseDto;
import com.juanCarlos.hardwareHub.entity.MontajeEntity;
import com.juanCarlos.hardwareHub.repository.MontajeRepository;
import com.juanCarlos.hardwareHub.service.MontajeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * Implementacion del servicio de la entidad MontajeEntity
 *
 * @see MontajeService
 * @author Juan Carlos
 */
@Service
@Transactional
@AllArgsConstructor
public class MontajeServiceImplementation implements MontajeService {

    private final MontajeRepository montajeRepository;
    private final MontajeMapper montajeMapper;
    private final GenericSearchService searchService;

    @Override
    public MontajeResponseDto create(MontajeRequestDto requestDto) {
        MontajeEntity entity = montajeMapper.toEntity(requestDto);
        MontajeEntity savedEntity = montajeRepository.save(entity);
        return montajeMapper.toResponseDto(savedEntity);
    }

    @Override
    public MontajeResponseDto getById(Long id) {
        MontajeEntity entity = montajeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ningun montaje con ese id"));
        return montajeMapper.toResponseDto(entity);
    }

    @Override
    public Page<MontajeResponseDto> searchAll(String filter, int page, int size, String sort) {
        Page<MontajeEntity> result = searchService.search(
                montajeRepository, filter, page, size, sort,
                MontajeFilterFields.ALLOWED_FIELDS);
        return result.map(montajeMapper::toResponseDto);
    }

    @Override
    public MontajeResponseDto update(Long id, MontajeRequestDto requestDto) {
        if (!montajeRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ningun montaje con ese id");
        }

        MontajeEntity entity = montajeMapper.toEntity(requestDto);
        entity.setId(id);
        MontajeEntity savedEntity = montajeRepository.save(entity);
        return montajeMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!montajeRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ningun montaje con ese id");
        }
        montajeRepository.deleteById(id);
    }

}
