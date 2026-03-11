package com.juanCarlos.hardwareHub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

/**
 * Clase que representa a la tabla comentario de la base de datos.
 *
 * @author Juan Carlos
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Null
    @ManyToOne
    @JoinColumn(name = "id_comentario")
    private ComentarioEntity comentario;

    @Null
    @ManyToOne
    @JoinColumn(name = "id_publicacion")
    private PublicacionEntity publicacion;
}
