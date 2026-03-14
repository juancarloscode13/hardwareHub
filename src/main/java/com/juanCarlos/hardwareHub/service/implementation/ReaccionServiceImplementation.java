package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dto.response.ReaccionConteoDto;
import com.juanCarlos.hardwareHub.entity.PublicacionEntity;
import com.juanCarlos.hardwareHub.entity.ReaccionEntity;
import com.juanCarlos.hardwareHub.entity.ReaccionId;
import com.juanCarlos.hardwareHub.entity.UsuarioEntity;
import com.juanCarlos.hardwareHub.entity.enums.TipoReaccion;
import com.juanCarlos.hardwareHub.repository.PublicacionRepository;
import com.juanCarlos.hardwareHub.repository.ReaccionRepository;
import com.juanCarlos.hardwareHub.repository.UsuarioRepository;
import com.juanCarlos.hardwareHub.service.ReaccionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ReaccionServiceImplementation implements ReaccionService {

    private final ReaccionRepository reaccionRepository;
    private final PublicacionRepository publicacionRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public ReaccionConteoDto addOrUpdateReaction(Long publicacionId, Long usuarioId, TipoReaccion tipo) {
        PublicacionEntity publicacion = publicacionRepository.findById(publicacionId)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ninguna publicación con id: " + publicacionId));
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NoSuchElementException("No se pudo encontrar ningún usuario con id: " + usuarioId));

        Optional<ReaccionEntity> existente = reaccionRepository.findByUsuarioIdAndPublicacionId(usuarioId, publicacionId);

        if (existente.isPresent()) {
            // Actualizar el tipo de reacción existente
            existente.get().setTipo(tipo);
            reaccionRepository.save(existente.get());
        } else {
            // Crear nueva reacción
            ReaccionId id = new ReaccionId(usuarioId, publicacionId);
            ReaccionEntity nueva = new ReaccionEntity(id, usuario, publicacion, tipo);
            reaccionRepository.save(nueva);
        }

        return buildConteoDto(publicacionId);
    }

    @Override
    public void removeReaction(Long publicacionId, Long usuarioId) {
        if (!publicacionRepository.existsById(publicacionId)) {
            throw new NoSuchElementException("No se pudo encontrar ninguna publicación con id: " + publicacionId);
        }
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new NoSuchElementException("No se pudo encontrar ningún usuario con id: " + usuarioId);
        }
        ReaccionId id = new ReaccionId(usuarioId, publicacionId);
        reaccionRepository.deleteById(id);
    }

    @Override
    public ReaccionConteoDto getReactionsByPublication(Long publicacionId) {
        if (!publicacionRepository.existsById(publicacionId)) {
            throw new NoSuchElementException("No se pudo encontrar ninguna publicación con id: " + publicacionId);
        }
        return buildConteoDto(publicacionId);
    }

    // ---- Helpers ----

    /** Construye el DTO de conteo usando queries por tipo para evitar cargar la colección completa. */
    private ReaccionConteoDto buildConteoDto(Long publicacionId) {
        return new ReaccionConteoDto(
                publicacionId,
                reaccionRepository.countByPublicacionIdAndTipo(publicacionId, TipoReaccion.LIKE),
                reaccionRepository.countByPublicacionIdAndTipo(publicacionId, TipoReaccion.DISLIKE),
                reaccionRepository.countByPublicacionIdAndTipo(publicacionId, TipoReaccion.LOVE),
                reaccionRepository.countByPublicacionIdAndTipo(publicacionId, TipoReaccion.FUNNY),
                reaccionRepository.countByPublicacionIdAndTipo(publicacionId, TipoReaccion.INTERESTING)
        );
    }
}

