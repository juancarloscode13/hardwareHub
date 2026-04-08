package com.juanCarlos.hardwareHub.repository;

import com.juanCarlos.hardwareHub.entity.ReaccionEntity;
import com.juanCarlos.hardwareHub.entity.ReaccionId;
import com.juanCarlos.hardwareHub.entity.enums.TipoReaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio correspondiente a la entidad ReaccionEntity
 *
 * @see ReaccionEntity
 * @author Juan Carlos
 */
public interface ReaccionRepository extends JpaRepository<ReaccionEntity, ReaccionId> {

    List<ReaccionEntity> findByPublicacionId(@Param("id") Long id);

    List<ReaccionEntity> findByUsuarioId(@Param("id") Long id);

    Optional<ReaccionEntity> findByUsuarioIdAndPublicacionId(Long usuarioId, Long publicacionId);

    /** Cuenta reacciones de un tipo concreto en una publicación. */
    @Query("SELECT COUNT(r) FROM ReaccionEntity r WHERE r.publicacion.id = :publicacionId AND r.tipo = :tipo")
    int countByPublicacionIdAndTipo(@Param("publicacionId") Long publicacionId, @Param("tipo") TipoReaccion tipo);
}

