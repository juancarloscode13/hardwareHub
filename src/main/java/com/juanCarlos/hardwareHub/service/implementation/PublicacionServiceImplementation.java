package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dsl.filters.PublicacionFilterFields;
import com.juanCarlos.hardwareHub.dsl.search.GenericSearchService;
import com.juanCarlos.hardwareHub.dto.mappers.PublicacionMapper;
import com.juanCarlos.hardwareHub.dto.request.PublicacionRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PublicacionResponseDto;
import com.juanCarlos.hardwareHub.entity.PublicacionEntity;
import com.juanCarlos.hardwareHub.entity.ReaccionEntity;
import com.juanCarlos.hardwareHub.entity.enums.TipoReaccion;
import com.juanCarlos.hardwareHub.repository.PublicacionRepository;
import com.juanCarlos.hardwareHub.service.PublicacionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class PublicacionServiceImplementation implements PublicacionService {

    private final PublicacionRepository publicacionRepository;
    private final PublicacionMapper publicacionMapper;
    private final GenericSearchService searchService;

    @Override
    public PublicacionResponseDto create(PublicacionRequestDto requestDto) {
        PublicacionEntity entity = publicacionMapper.toEntity(requestDto);
        validateMontajeMultimediaRule(entity);
        entity.setFecha(LocalDateTime.now());
        PublicacionEntity savedEntity = publicacionRepository.save(entity);
        return enrichWithCounts(publicacionMapper.toResponseDto(savedEntity), savedEntity);
    }

    @Override
    public PublicacionResponseDto getById(Long id) {
        PublicacionEntity entity = publicacionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ninguna publicación con id: " + id));
        return enrichWithCounts(publicacionMapper.toResponseDto(entity), entity);
    }

    @Override
    public Page<PublicacionResponseDto> searchAll(String filter, int page, int size, String sort) {
        Page<PublicacionEntity> result = searchService.search(
                publicacionRepository, filter, page, size, sort,
                PublicacionFilterFields.ALLOWED_FIELDS);
        return result.map(entity -> enrichWithCounts(publicacionMapper.toResponseDto(entity), entity));
    }

    @Override
    public PublicacionResponseDto update(Long id, PublicacionRequestDto requestDto) {
        PublicacionEntity existingEntity = publicacionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar ninguna publicación con id: " + id));

        PublicacionEntity entity = publicacionMapper.toEntity(requestDto);
        entity.setId(id);
        entity.setFecha(existingEntity.getFecha());
        validateMontajeMultimediaRule(entity);
        PublicacionEntity savedEntity = publicacionRepository.save(entity);
        return enrichWithCounts(publicacionMapper.toResponseDto(savedEntity), savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!publicacionRepository.existsById(id)) {
            throw new EntityNotFoundException("No se pudo encontrar ninguna publicación con id: " + id);
        }
        publicacionRepository.deleteById(id);
    }

    // ---- Helpers ----

    private void validateMontajeMultimediaRule(PublicacionEntity entity) {
        boolean hasMontaje   = entity.getMontaje()    != null;
        boolean hasMultimedia = entity.getMultimedia() != null;
        if (hasMontaje && hasMultimedia) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Una publicación no puede tener montaje y multimedia al mismo tiempo");
        }
    }

    /** Calcula los conteos de reacciones desde la colección lazy (dentro de la transacción). */
    private PublicacionResponseDto enrichWithCounts(PublicacionResponseDto dto, PublicacionEntity entity) {
        Set<ReaccionEntity> reacciones = entity.getReacciones();
        dto.setLikesCount(count(reacciones, TipoReaccion.LIKE));
        dto.setDislikesCount(count(reacciones, TipoReaccion.DISLIKE));
        dto.setLoveCount(count(reacciones, TipoReaccion.LOVE));
        dto.setFunnyCount(count(reacciones, TipoReaccion.FUNNY));
        dto.setInterestingCount(count(reacciones, TipoReaccion.INTERESTING));
        return dto;
    }

    private int count(Set<ReaccionEntity> reacciones, TipoReaccion tipo) {
        return (int) reacciones.stream().filter(r -> r.getTipo() == tipo).count();
    }
}
