package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dto.mappers.MontajeMapper;
import com.juanCarlos.hardwareHub.dto.request.MontajeRequestDto;
import com.juanCarlos.hardwareHub.dto.response.MontajeResponseDto;
import com.juanCarlos.hardwareHub.entity.MontajeEntity;
import com.juanCarlos.hardwareHub.repository.MontajeRepository;
import com.juanCarlos.hardwareHub.service.MontajeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class MontajeServiceImplementation implements MontajeService {

    private final MontajeRepository montajeRepository;
    private final MontajeMapper montajeMapper;

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
    public List<MontajeResponseDto> getAll() {
        List<MontajeEntity> entities = montajeRepository.findAll();
        return montajeMapper.toResponseDtoList(entities);
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

    @Override
    public List<MontajeResponseDto> getByUsuarioId(Long usuarioId) {
        List<MontajeEntity> entities = montajeRepository.getByUsuarioId(usuarioId);
        return montajeMapper.toResponseDtoList(entities);
    }
}
