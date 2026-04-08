package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dsl.filters.RefrigeracionFilterFields;
import com.juanCarlos.hardwareHub.dsl.search.GenericSearchService;
import com.juanCarlos.hardwareHub.dto.mappers.RefrigeracionMapper;
import com.juanCarlos.hardwareHub.dto.request.RefrigeracionRequestDto;
import com.juanCarlos.hardwareHub.dto.response.RefrigeracionResponseDto;
import com.juanCarlos.hardwareHub.entity.RefrigeracionEntity;
import com.juanCarlos.hardwareHub.repository.RefrigeracionRepository;
import com.juanCarlos.hardwareHub.service.RefrigeracionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * Implementacion del servicio de la entidad RefrigeracionEntity
 *
 * @see RefrigeracionService
 * @author Juan Carlos
 */
@Service
@Transactional
@AllArgsConstructor
public class RefrigeracionServiceImplementation implements RefrigeracionService {

    private final RefrigeracionRepository refrigeracionRepository;
    private final RefrigeracionMapper refrigeracionMapper;
    private final GenericSearchService searchService;

    @Override
    public RefrigeracionResponseDto create(RefrigeracionRequestDto requestDto) {
        RefrigeracionEntity entity = refrigeracionMapper.toEntity(requestDto);
        RefrigeracionEntity savedEntity = refrigeracionRepository.save(entity);
        return refrigeracionMapper.toResponseDto(savedEntity);
    }

    @Override
    public RefrigeracionResponseDto getById(Long id) {
        RefrigeracionEntity entity = refrigeracionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ninguna refrigeracion con ese id"));
        return refrigeracionMapper.toResponseDto(entity);
    }

    @Override
    public Page<RefrigeracionResponseDto> searchAll(String filter, int page, int size, String sort) {
        Page<RefrigeracionEntity> result = searchService.search(
                refrigeracionRepository, filter, page, size, sort,
                RefrigeracionFilterFields.ALLOWED_FIELDS);
        return result.map(refrigeracionMapper::toResponseDto);
    }

    @Override
    public RefrigeracionResponseDto update(Long id, RefrigeracionRequestDto requestDto) {
        if (!refrigeracionRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna refrigeracion con ese id");
        }

        RefrigeracionEntity entity = refrigeracionMapper.toEntity(requestDto);
        entity.setId(id);
        RefrigeracionEntity savedEntity = refrigeracionRepository.save(entity);
        return refrigeracionMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!refrigeracionRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna refrigeracion con ese id");
        }
        refrigeracionRepository.deleteById(id);
    }
}
