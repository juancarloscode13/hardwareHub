package com.juanCarlos.hardwareHub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase que representa a la tabla publicacion de la base de datos.
 *
 * <p>La colección {@code reacciones} se excluye de equals/hashCode/toString
 * para evitar bucles de recursión con {@link ReaccionEntity}.</p>
 *
 * @author Juan Carlos
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"reacciones"})
@ToString(exclude = {"reacciones"})
@Entity
@Table(name = "publicacion")
public class PublicacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contenido_texto")
    private String contenidoTexto;

    @Lob
    @Column(name = "multimedia")
    private byte[] multimedia;

    @ManyToOne
    @JoinColumn(name = "id_montaje")
    private MontajeEntity montaje;

    @NonNull
    @Column(name = "fecha")
    private LocalDateTime fecha;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

    /** Reacciones recibidas por esta publicación. */
    @OneToMany(mappedBy = "publicacion", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<ReaccionEntity> reacciones = new HashSet<>();
}
