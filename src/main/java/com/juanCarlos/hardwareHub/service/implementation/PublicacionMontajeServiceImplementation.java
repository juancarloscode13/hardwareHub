package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dto.mappers.PublicacionMontajeMapper;
import com.juanCarlos.hardwareHub.dto.request.PublicacionMontajeRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PublicacionMontajeResponseDto;
import com.juanCarlos.hardwareHub.entity.PublicacionMontajeEntity;
import com.juanCarlos.hardwareHub.repository.PublicacionMontajeRepository;
import com.juanCarlos.hardwareHub.service.PublicacionMontajeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.time.LocalDateTime;

@Service
@Transactional
@AllArgsConstructor
public class PublicacionMontajeServiceImplementation implements PublicacionMontajeService {

    private final PublicacionMontajeRepository publicacionMontajeRepository;
    private final PublicacionMontajeMapper publicacionMontajeMapper;

    @Override
    public PublicacionMontajeResponseDto create(PublicacionMontajeRequestDto requestDto) {
        PublicacionMontajeEntity entity = publicacionMontajeMapper.toEntity(requestDto);
        if (entity.getLikes() == null) {
            entity.setLikes(0);
        }
        entity.setFecha(LocalDateTime.now());
        PublicacionMontajeEntity savedEntity = publicacionMontajeRepository.save(entity);
        return publicacionMontajeMapper.toResponseDto(savedEntity);
    }

    @Override
    public PublicacionMontajeResponseDto getById(Long id) {
        PublicacionMontajeEntity entity = publicacionMontajeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ninguna publicacion de montaje con ese id"));
        return publicacionMontajeMapper.toResponseDto(entity);
    }

    @Override
    public List<PublicacionMontajeResponseDto> getAll() {
        List<PublicacionMontajeEntity> entities = publicacionMontajeRepository.findAll();
        return publicacionMontajeMapper.toResponseDtoList(entities);
    }

    @Override
    public PublicacionMontajeResponseDto update(Long id, PublicacionMontajeRequestDto requestDto) {
        PublicacionMontajeEntity existingEntity = publicacionMontajeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar ninguna publicacion de montaje con ese id"));

        PublicacionMontajeEntity entity = publicacionMontajeMapper.toEntity(requestDto);
        entity.setId(id);
        entity.setFecha(existingEntity.getFecha());
        entity.setLikes(entity.getLikes() == null ? existingEntity.getLikes() : entity.getLikes());
        PublicacionMontajeEntity savedEntity = publicacionMontajeRepository.save(entity);
        return publicacionMontajeMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!publicacionMontajeRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna publicacion de montaje con ese id");
        }
        publicacionMontajeRepository.deleteById(id);
    }

    @Override
    public List<PublicacionMontajeResponseDto> getAllOrderByFechaDesc() {
        List<PublicacionMontajeEntity> entities = publicacionMontajeRepository.findAllByOrderByFechaDesc();
        return publicacionMontajeMapper.toResponseDtoList(entities);
    }

    @Override
    public List<PublicacionMontajeResponseDto> getByUsuarioId(Long usuarioId) {
        List<PublicacionMontajeEntity> entities = publicacionMontajeRepository.getByUsuarioId(usuarioId);
        return publicacionMontajeMapper.toResponseDtoList(entities);
    }
}
