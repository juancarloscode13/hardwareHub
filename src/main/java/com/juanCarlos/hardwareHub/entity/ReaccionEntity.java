package com.juanCarlos.hardwareHub.entity;

import com.juanCarlos.hardwareHub.entity.enums.TipoReaccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidad que representa una reacción de un usuario sobre una publicación.
 * La clave primaria es compuesta (id_usuario, id_publicacion), garantizando
 * que cada usuario solo puede tener una reacción por publicación.
 *
 * @see PublicacionEntity
 * @see ReaccionId
 * @author Juan Carlos
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"usuario", "publicacion"})
@ToString(exclude = {"usuario", "publicacion"})
@Entity
@Table(name = "reaccion")
public class ReaccionEntity {

    @EmbeddedId
    private ReaccionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("usuarioId")
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("publicacionId")
    @JoinColumn(name = "id_publicacion")
    private PublicacionEntity publicacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoReaccion tipo;
}

