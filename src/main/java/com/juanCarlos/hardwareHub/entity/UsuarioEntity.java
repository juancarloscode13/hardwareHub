package com.juanCarlos.hardwareHub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juanCarlos.hardwareHub.entity.enums.UsuarioRol;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase que representa a la tabla usuario de la base de datos.
 *
 * @author Juan Carlos
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"usuariosSeguidos", "seguidores", "reacciones"})
@ToString(exclude = {"usuariosSeguidos", "seguidores", "reacciones"})
@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "nombre")
    private String nombre;

    @NonNull
    @Column(name = "email")
    private String email;

    @Null
    @Column(name = "contraseña")
    private String contrasena;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "rol")
    private UsuarioRol rol;

    /** Usuarios que este usuario sigue. */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuario_seguidor",
            joinColumns = @JoinColumn(name = "id_seguidor"),
            inverseJoinColumns = @JoinColumn(name = "id_seguido")
    )
    @JsonIgnore
    private Set<UsuarioEntity> usuariosSeguidos = new HashSet<>();

    /** Usuarios que siguen a este usuario. */
    @ManyToMany(mappedBy = "usuariosSeguidos", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UsuarioEntity> seguidores = new HashSet<>();

    /** Reacciones realizadas por este usuario. */
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<ReaccionEntity> reacciones = new HashSet<>();
}
