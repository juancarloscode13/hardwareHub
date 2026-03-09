package com.juanCarlos.hardwareHub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "publicacion_montaje")
public class PublicacionMontajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Null
    @Column(name = "contenido_texto")
    private String contenidoTexto;

    @Null
    @Lob
    @Column(name = "multimedia")
    private byte[] multimedia;

    @NonNull
    @Column(name = "likes")
    private Integer likes;

    @NonNull
    @Column(name = "fecha")
    private LocalDateTime fecha;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_montaje")
    private MontajeEntity montaje;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;
}
