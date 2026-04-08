package com.juanCarlos.hardwareHub.service;

import com.juanCarlos.hardwareHub.dto.response.ReaccionConteoDto;
import com.juanCarlos.hardwareHub.entity.ReaccionEntity;
import com.juanCarlos.hardwareHub.entity.enums.TipoReaccion;

/**
 * Servicio correspondiente a la entidad ReaccionEntity
 *
 * @see ReaccionEntity
 * @author Juan Carlos
 */
public interface ReaccionService {

    /**
     * Añade una nueva reacción o actualiza la existente si el usuario ya reaccionó.
     *
     * @param publicacionId ID de la publicación
     * @param usuarioId     ID del usuario que reacciona
     * @param tipo          Tipo de reacción
     * @return Conteo actualizado de reacciones de la publicación
     */
    ReaccionConteoDto addOrUpdateReaction(Long publicacionId, Long usuarioId, TipoReaccion tipo);

    /**
     * Elimina la reacción del usuario sobre la publicación.
     * No lanza error si la reacción no existía.
     *
     * @param publicacionId ID de la publicación
     * @param usuarioId     ID del usuario
     */
    void removeReaction(Long publicacionId, Long usuarioId);

    /**
     * Devuelve el conteo de reacciones agrupadas por tipo para una publicación.
     *
     * @param publicacionId ID de la publicación
     * @return DTO con los contadores por tipo de reacción
     */
    ReaccionConteoDto getReactionsByPublication(Long publicacionId);
}

