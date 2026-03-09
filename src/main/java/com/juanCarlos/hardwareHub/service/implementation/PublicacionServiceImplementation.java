package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dto.mappers.PublicacionMapper;
import com.juanCarlos.hardwareHub.dto.request.PublicacionRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PublicacionResponseDto;
import com.juanCarlos.hardwareHub.entity.PublicacionEntity;
import com.juanCarlos.hardwareHub.repository.PublicacionRepository;
import com.juanCarlos.hardwareHub.service.PublicacionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class PublicacionServiceImplementation implements PublicacionService {

    private final PublicacionRepository publicacionRepository;
    private final PublicacionMapper publicacionMapper;

    @Override
    public PublicacionResponseDto create(PublicacionRequestDto requestDto) {
        PublicacionEntity entity = publicacionMapper.toEntity(requestDto);
        validateMontajeMultimediaRule(entity);
        if (entity.getLikes() == null) {
            entity.setLikes(0);
        }
        entity.setFecha(LocalDateTime.now());
        PublicacionEntity savedEntity = publicacionRepository.save(entity);
        return publicacionMapper.toResponseDto(savedEntity);
    }

    @Override
    public PublicacionResponseDto getById(Long id) {
        PublicacionEntity entity = publicacionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ninguna publicacion con ese id"));
        return publicacionMapper.toResponseDto(entity);
    }

    @Override
    public List<PublicacionResponseDto> getAll() {
        List<PublicacionEntity> entities = publicacionRepository.findAll();
        return publicacionMapper.toResponseDtoList(entities);
    }

    @Override
    public PublicacionResponseDto update(Long id, PublicacionRequestDto requestDto) {
        PublicacionEntity existingEntity = publicacionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar ninguna publicacion con ese id"));

        PublicacionEntity entity = publicacionMapper.toEntity(requestDto);
        entity.setId(id);
        entity.setFecha(existingEntity.getFecha());
        entity.setLikes(entity.getLikes() == null ? existingEntity.getLikes() : entity.getLikes());
        validateMontajeMultimediaRule(entity);
        PublicacionEntity savedEntity = publicacionRepository.save(entity);
        return publicacionMapper.toResponseDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!publicacionRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna publicacion con ese id");
        }
        publicacionRepository.deleteById(id);
    }

    @Override
    public List<PublicacionResponseDto> getAllOrderByFechaDesc() {
        List<PublicacionEntity> entities = publicacionRepository.findAllByOrderByFechaDesc();
        return publicacionMapper.toResponseDtoList(entities);
    }

    @Override
    public List<PublicacionResponseDto> getByUsuarioId(Long usuarioId) {
        List<PublicacionEntity> entities = publicacionRepository.getByUsuarioId(usuarioId);
        return publicacionMapper.toResponseDtoList(entities);
    }

    @Override
    public List<PublicacionResponseDto> getByMontajeId(Long montajeId) {
        List<PublicacionEntity> entities = publicacionRepository.getByMontajeId(montajeId);
        return publicacionMapper.toResponseDtoList(entities);
    }

    private void validateMontajeMultimediaRule(PublicacionEntity entity) {
        boolean hasMontaje = entity.getMontaje() != null;
        boolean hasMultimedia = entity.getMultimedia() != null;

        if (hasMontaje && hasMultimedia) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Una publicacion no puede tener montaje y multimedia al mismo tiempo"
            );
        }
    }
}
