package com.juanCarlos.hardwareHub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a la tabla conversation de la base de datos.
 * Modela una conversación de mensajería directa entre dos usuarios.
 *
 * @see MessageEntity
 * @author Juan Carlos
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"messages"})
@ToString(exclude = {"messages"})
@Entity
@Table(name = "conversation")
public class ConversationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_a_id")
    private UsuarioEntity userA;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_b_id")
    private UsuarioEntity userB;

    @NonNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "conversation", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MessageEntity> messages = new ArrayList<>();
}

