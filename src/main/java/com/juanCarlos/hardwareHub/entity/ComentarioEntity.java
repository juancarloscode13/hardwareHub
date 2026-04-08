package com.juanCarlos.hardwareHub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Clase que representa a la tabla comentario de la base de datos.
 *
 * <p>El campo {@code comentario} es una auto-referencia (comentario padre). Un comentario puede ser respuesta a otro comentario</p>
 *
 * @author Juan Carlos
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"comentario"})
@ToString(exclude = {"comentario"})
@Entity
@Table(name = "comentario")
public class ComentarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "texto_contenido")
    private String textoContenido;

    @NonNull
    @Column(name = "likes")
    private Integer likes;

    @NonNull
    @Column(name = "fecha")
    private LocalDateTime fecha;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "id_comentario")
    private ComentarioEntity comentario;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_publicacion")
    private PublicacionEntity publicacion;
}
