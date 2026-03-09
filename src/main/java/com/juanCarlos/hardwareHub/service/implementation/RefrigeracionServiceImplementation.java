package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dto.mappers.RefrigeracionMapper;
import com.juanCarlos.hardwareHub.dto.request.RefrigeracionRequestDto;
import com.juanCarlos.hardwareHub.dto.response.RefrigeracionResponseDto;
import com.juanCarlos.hardwareHub.entity.RefrigeracionEntity;
import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
import com.juanCarlos.hardwareHub.repository.RefrigeracionRepository;
import com.juanCarlos.hardwareHub.service.RefrigeracionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class RefrigeracionServiceImplementation implements RefrigeracionService {

    private final RefrigeracionRepository refrigeracionRepository;
    private final RefrigeracionMapper refrigeracionMapper;

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
    public List<RefrigeracionResponseDto> getAll() {
        List<RefrigeracionEntity> entities = refrigeracionRepository.findAll();
        return refrigeracionMapper.toResponseDtoList(entities);
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

    @Override
    public List<RefrigeracionResponseDto> getBySocketCompatible(CpuSocket socketCompatible) {
        List<RefrigeracionEntity> entities = refrigeracionRepository.getBySocketCompatible(socketCompatible);
        return refrigeracionMapper.toResponseDtoList(entities);
    }
}
