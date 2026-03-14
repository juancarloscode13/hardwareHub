package com.juanCarlos.hardwareHub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Clase que representa a la tabla comentario de la base de datos.
 *
 * <p>El campo {@code comentario} es una auto-referencia (comentario padre).
 * Se excluye explícitamente de {@code hashCode}, {@code equals} y {@code toString}
 * para evitar bucles infinitos cuando existe una cadena cíclica A→B→A.</p>
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

    /**
     * Comentario padre (auto-referencia).
     * Excluido de hashCode/equals/toString para evitar recursión infinita.
     */
    @Null
    @ManyToOne
    @JoinColumn(name = "id_comentario")
    private ComentarioEntity comentario;

    @Null
    @ManyToOne
    @JoinColumn(name = "id_publicacion")
    private PublicacionEntity publicacion;
}
