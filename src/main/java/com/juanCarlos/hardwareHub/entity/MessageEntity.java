package com.juanCarlos.hardwareHub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Clase que representa a la tabla message de la base de datos.
 * Modela un mensaje individual dentro de una conversación.
 *
 * @author Juan Carlos
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "message")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    private ConversationEntity conversation;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_id")
    private UsuarioEntity sender;

    @NonNull
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @NonNull
    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(name = "is_read")
    private boolean isRead;
}

